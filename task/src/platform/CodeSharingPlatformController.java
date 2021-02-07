package platform;

import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.*;

@Controller
public class CodeSharingPlatformController {

    private TreeMap<Integer, CodeSharingPlatformModel> codebase = new TreeMap<>();
    private Gson gson = new Gson();

    @GetMapping(value = "/code/new")
    public String getNewHTML() {
       return "create";
    }

    @GetMapping(value = "/code/{N}", produces = "text/html")
    public String GetCodeN(Model model, @PathVariable Integer N) {

        SortedMap<Integer, CodeSharingPlatformModel> latestCode = codebase.subMap(N, N + 1);

        model.addAttribute("title", "Code");
        model.addAttribute("latestCode", latestCode);

        return "index";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String GetCodeLatest(Model model) {
        SortedMap<Integer, CodeSharingPlatformModel> latestCode = new TreeMap<>();

        //get latest values in descending order (need all values - id, code and date; the template logic will handle the id value)
        latestCode = (codebase.size() - 10) >= 0 ? codebase.descendingMap().headMap(codebase.size() - 10) : codebase.descendingMap();

        model.addAttribute("title", "Latest");
        model.addAttribute("latestCode", latestCode);

        return "index";
    }

    @GetMapping("/api/code/{N}")
    @ResponseBody
    public CodeSharingPlatformModel GetApiCodeN(@PathVariable Integer N)
    {
        return codebase.get(N);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public Collection<CodeSharingPlatformModel> GetApiCodeLatest( ) {
        Collection<CodeSharingPlatformModel> latestCode = new ArrayList<>();

        //get latest values in descending order (without the key id values, only need the code and date values)
        latestCode = (codebase.size() - 10) >= 0 ? codebase.descendingMap().headMap(codebase.size() - 10).values() : codebase.descendingMap().values();

        return latestCode;

    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, String> postJSON(@RequestBody String newCode){
        Integer newId = codebase.size() + 1;

        //get values from JSON object into map and save the code value of key code
        Map<String, String> codeFromJson = gson.fromJson(newCode, new TypeToken<Map<String,String>>(){}.getType());
        codebase.put(newId, new CodeSharingPlatformModel(codeFromJson.get("code")));

        //return body in JSON format with an object that has an attribute "id" and value = the id of the created snippet
        HashMap<String, String> id = new HashMap<>();
        id.put("id", newId.toString());
        return id;
    }
}
