package platform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CodeSharingPlatformModel  {

    @Id
    @Column
    private String id;
    @Column
    private String code;
    @Column
    private String date = LocalDateTime.now().toString();
    @Column
    private Integer time = 0;

    public CodeSharingPlatformModel( ) {

    }

    public CodeSharingPlatformModel(String id, String code, Integer time, Integer views) {
        this.id = id;
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Column
    private Integer views = 0;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
