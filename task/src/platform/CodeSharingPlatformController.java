package platform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CodeSharingPlatformController {

    private List<CodeSharingPlatformModel> codebase = new ArrayList<>();
    private Gson gson = new Gson();

    @GetMapping("/code/new")
    public ResponseEntity<String> getNewHTML() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codebase.get(codebase.size() - 1).setCodeHTML());
    }

    @GetMapping("/api/code")
    public ResponseEntity<String> HeadersJSON( ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(gson.toJson(codebase));
    }


    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String postJSON(@RequestBody String newCode){
        Integer newId = codebase.size() == 0 ? 0 : codebase.get(codebase.size() - 1).getId() + 1;
        codebase.add(new CodeSharingPlatformModel(newCode, newId));
        return gson.toJson(newId);
    }

}
