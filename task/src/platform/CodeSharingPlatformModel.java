package platform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CodeSharingPlatformModel  {

    @Id
    @Column
    private Integer id;
    @Column
    private String code;
    @Column
    private String date = LocalDateTime.now().toString();

    public CodeSharingPlatformModel( ) {

    }

    public CodeSharingPlatformModel(Integer id, String code) {
        this.code = code;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
