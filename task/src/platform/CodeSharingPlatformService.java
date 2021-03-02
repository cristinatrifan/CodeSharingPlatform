package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSharingPlatformService implements ICodeSharingPlatformService{

    private CodeSharingPlatformRepository codebase;
    private Boolean toDelete = false;
    @Autowired
    public CodeSharingPlatformService(CodeSharingPlatformRepository codebase) {
        this.codebase = codebase;
    }

    @Override
    public List<ICodeSharingPlatformProjection> getLatestCodeSnippets() {
        //get latest values in descending order - max 10 values
         List<ICodeSharingPlatformProjection> model = codebase.findByDateByOrderByDateDesc();
         return model;
    }

    // get values for 1 code snippet, update time/views or delete before display,
    //throw 404 exception if the code snippet was deleted
    @Override
    public ICodeSharingPlatformProjection getUUIDCodeSnippet(String UUID) throws ResponseStatusException {

        try {
            toDelete = false;
            toDelete = updateOrDeleteCodeSnippet(UUID);
            ICodeSharingPlatformProjection model = codebase.findByIdProj(UUID);
            if (model == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "404 Not Found");
            }
            else return model;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "404 Not Found");
        }
        finally {
            //this applies to the situation when number of views was more than 0
            //and now it is 0, so it should be deleted after display
            if (toDelete) {
                CodeSharingPlatformModel model = codebase.findById(UUID).get();
                codebase.delete(model);
            }
        }
    }

    @Override
    public String createNewCodeSnippet(String newCode, Integer time, Integer views) {
        CodeSharingPlatformModel model = codebase.save(new CodeSharingPlatformModel(UUID.randomUUID().toString(), newCode, time, views));
        return model.getId();
    }

    @Override
    public Boolean updateOrDeleteCodeSnippet(String UUID) throws ResponseStatusException {
        CodeSharingPlatformModel model = codebase.findById(UUID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "404 Not Found"));

        if (model.getViews() - 1 >= 0) {
            model.setViews(model.getViews() - 1);
            if (model.getViews() == 0) {
                toDelete = true;
            }
            codebase.save(model);
        }

        //if there is still time left for displaying the code snippet
        if (model.getTime() > 0 && LocalDateTime.parse(model.getDate()).plusSeconds(model.getTime()).isAfter(LocalDateTime.now())) {

            //set the new time left
            model.setTime((model.getTime() - Math.toIntExact(ChronoUnit.SECONDS.between(LocalDateTime.parse(model.getDate()), LocalDateTime.now()))));
            if (model.getTime() <= 0) {
                codebase.delete(model);
            }
            else codebase.save(model);
        }
        else if (model.getTime() > 0) {
            codebase.delete(model);
        }

        return toDelete;
    }

    @Override
    public Boolean checkDeletionStatus() {
        return toDelete;
    }
}
