package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeSharingPlatformService implements ICodeSharingPlatformService{

    private CodeSharingPlatformRepository codebase;
    @Autowired
    public CodeSharingPlatformService(CodeSharingPlatformRepository codebase) {
        this.codebase = codebase;
    }

    @Override
    public List<ICodeSharingPlatformProjection> getLatestCodeSnippets() {
        //get latest values in descending order (without the key id values, only need the code and date values)

         List<ICodeSharingPlatformProjection> model = (codebase.count() - 10) >= 0
                ? codebase.findByIdGreaterThanByOrderByIdDesc(Math.toIntExact(codebase.count() - 10))
                 : codebase.findAllByOrderByIdDesc();
         return model;
    }

    @Override
    public ICodeSharingPlatformProjection getNthCodeSnippet(Integer N) {
        ICodeSharingPlatformProjection model = codebase.findByIdProj(N);
        return model;
    }

    @Override
    public String createNewCodeSnippet(String newCode) {
        CodeSharingPlatformModel model = codebase.save(new CodeSharingPlatformModel(Math.toIntExact(codebase.count() + 1), newCode));
        return model.getId().toString();
    }
}
