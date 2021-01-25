package platform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.*;

@RestController
public class CodeSharingPlatformController {

    private ArrayList <CodeSharingPlatformModel> codebase = new ArrayList<>();
    private Gson gson = new Gson();

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getNewHTML(Model model) {

        model.addAttribute("title", "Code");
        model.addAttribute("date", codebase.get(codebase.size() - 1).getDate());
        model.addAttribute("code", codebase.get(codebase.size() - 1).getCode());

        return "index";
    }

    @GetMapping(value = "/code/{N}", produces = "text/html")
    public String GetCodeN(Model model, @PathVariable Integer N) {

        model.addAttribute("title", "Code");
        model.addAttribute("date", codebase.get(N - 1).getDate());
        model.addAttribute("code", codebase.get(N - 1).getCode());

        return "index";
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public List<CodeSharingPlatformModel> GetCodeLatest() {
        List<CodeSharingPlatformModel> latestCode = new ArrayList();
        latestCode = codebase.subList(codebase.size() - 10, codebase.size());
        Collections.sort(latestCode, Collections.reverseOrder());
        //title = latest
        return latestCode;
    }

    @GetMapping("/api/code")
    public ResponseEntity<String> HeadersJSON( ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(gson.toJson(codebase));
    }

    @GetMapping("/api/code/{N}")
    public CodeSharingPlatformModel GetApiCodeN(@PathVariable Integer N) {
        return codebase.get(N - 1);
    }

    @GetMapping("/api/code/latest")
    public List<CodeSharingPlatformModel> GetApiCodeLatest( ) {
        List<CodeSharingPlatformModel> latestCode = new ArrayList();
        latestCode = codebase.subList(codebase.size() - 10, codebase.size());
        Collections.sort(latestCode, Collections.reverseOrder());
        return latestCode;

    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String postJSON(@RequestBody String newCode){
        Integer newId = codebase.size() == 0 ? 0 : codebase.size();
        codebase.add(new CodeSharingPlatformModel(newCode, newId));
        return gson.toJson(codebase.get(newId).getId());
    }

}
