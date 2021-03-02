package platform;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CodeSharingPlatformController {

    private ICodeSharingPlatformService codeBase;

    @Autowired
    public CodeSharingPlatformController(ICodeSharingPlatformService codeBase) {
        this.codeBase = codeBase;
    }

    @GetMapping(value = "/code/new")
    public String getNewHTML() {
       return "create";
    }

    @GetMapping(value = "/code/{UUID}", produces = "text/html")
    public String GetCodeN(Model model, @PathVariable String UUID) throws Exception {

        List<ICodeSharingPlatformProjection> latestCode = new ArrayList<>();
        latestCode.add(codeBase.getUUIDCodeSnippet(UUID));

        model.addAttribute("title", "Code");
        model.addAttribute("latestCode", latestCode);
        model.addAttribute("displayView", codeBase.checkDeletionStatus());

        return "index";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String GetCodeLatest(Model model) {

        //get latest 10 or less values in descending order
        List<ICodeSharingPlatformProjection> latestCode = codeBase.getLatestCodeSnippets();

        model.addAttribute("title", "Latest");
        model.addAttribute("latestCode", latestCode);
        model.addAttribute("displayView", codeBase.checkDeletionStatus());

        return "index";
    }

    @GetMapping("/api/code/{UUID}")
    @ResponseBody
    public ICodeSharingPlatformProjection GetApiCodeN(@PathVariable String UUID) throws Exception {
            return codeBase.getUUIDCodeSnippet(UUID);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<ICodeSharingPlatformProjection> GetApiCodeLatest( ) {
        //get latest 10 or less values in descending order
        return codeBase.getLatestCodeSnippets();

    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, String> postJSON(@RequestBody String newValues) {
        Gson gson = new Gson();
        //get values from JSON object using the entity model
        CodeSharingPlatformModel codeFromJson = gson.fromJson(newValues, CodeSharingPlatformModel.class);

        String newId = codeBase.createNewCodeSnippet(codeFromJson.getCode(), codeFromJson.getTime(), codeFromJson.getViews());

        //return body in JSON format with an object that has an attribute "id" and value = the id of the created snippet
        HashMap<String, String> id = new HashMap<>();
        id.put("id", newId);
        return id;
    }
}