package platform;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CodeSharingPlatformModel {
    private String code = "public static void main(String[] args) {SpringApplication.run(CodeSharingPlatform.class, args);}";
    private String date = LocalDateTime.now().toString();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCodeHTML() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<pre id=\"code_snippet\">" +
                code +
                "</pre>" +
                "<span id=\"load_date\">" + date + "</span>" +
                "</body>\n" +
                "</html>\n";
    }

    public String setCodeHTML() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<textarea id=\"code_snippet\"> ... </textarea>" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>" +
                "<script>function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    \n" +
                "    let json = JSON.stringify(object);\n" +
                "    \n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    \n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "}\n</script>" +
                "</body>\n" +
                "</html>\n";
    }
}
