package platform;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CodeSharingPlatformController {

    private ICodeSharingPlatformService newcodebase;

    @Autowired
    public CodeSharingPlatformController(ICodeSharingPlatformService newcodebase) {
        this.newcodebase = newcodebase;
    }

    @GetMapping(value = "/code/new")
    public String getNewHTML() {
       return "create";
    }

    @GetMapping(value = "/code/{N}", produces = "text/html")
    public String GetCodeN(Model model, @PathVariable Integer N) {

        List<ICodeSharingPlatformProjection> latestCode = new ArrayList<>();
        latestCode.add(newcodebase.getNthCodeSnippet(N));

        model.addAttribute("title", "Code");
        model.addAttribute("latestCode", latestCode);

        return "index";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String GetCodeLatest(Model model) {

        //get latest values in descending order (only code and date)
        List<ICodeSharingPlatformProjection> latestCode = newcodebase.getLatestCodeSnippets();

        model.addAttribute("title", "Latest");
        model.addAttribute("latestCode", latestCode);

        return "index";
    }

    @GetMapping("/api/code/{N}")
    @ResponseBody
    public ICodeSharingPlatformProjection GetApiCodeN(@PathVariable Integer N)
    {
        return newcodebase.getNthCodeSnippet(N);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<ICodeSharingPlatformProjection> GetApiCodeLatest( ) {
        //get latest values in descending order (without the key id values, only need the code and date values)
        List<ICodeSharingPlatformProjection> latestCode = newcodebase.getLatestCodeSnippets();

        return latestCode;

    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, String> postJSON(@RequestBody String newCode){
        Gson gson = new Gson();
        //get values from JSON object into map and save the code value of key code
        Map<String, String> codeFromJson = gson.fromJson(newCode, new TypeToken<Map<String,String>>(){}.getType());
        String newId = newcodebase.createNewCodeSnippet(codeFromJson.get("code"));

        //return body in JSON format with an object that has an attribute "id" and value = the id of the created snippet
        HashMap<String, String> id = new HashMap<>();
        id.put("id", newId);
        return id;
    }
}
