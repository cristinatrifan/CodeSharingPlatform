package platform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CodeSharingPlatformController {

    //private List<CodeSharingPlatformModel> codebase = new ArrayList<>();
    CodeSharingPlatformModel codebase = new CodeSharingPlatformModel();
    private Gson gson = new Gson();

    @GetMapping("/code")
    public ResponseEntity<String> HeadersHTML() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");
        responseHeaders.set("test", "test");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codebase.getCodeHTML());
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> getNewHTML() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/html");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codebase.setCodeHTML());
    }

    @GetMapping("/api/code")
    public ResponseEntity<String> HeadersJSON( ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(gson.toJson(codebase));
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public void postJSON(@RequestBody String newCode, HttpServletResponse response) throws IOException {
        CodeSharingPlatformModel newCodeObj = gson.fromJson(newCode, CodeSharingPlatformModel.class);
        codebase.setCode(newCodeObj.getCode());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{}");
    }

}
