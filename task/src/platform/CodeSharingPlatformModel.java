package platform;

import java.time.LocalDateTime;

public class CodeSharingPlatformModel  {

    private String code = "public static void main(String[] args) {SpringApplication.run(CodeSharingPlatform.class, args);}";
    private String date = LocalDateTime.now().toString();

    public CodeSharingPlatformModel( ) {

    }

    public CodeSharingPlatformModel(String code) {
        this.code = code;
    }

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

}
