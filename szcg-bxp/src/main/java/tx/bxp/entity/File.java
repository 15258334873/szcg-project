package tx.bxp.entity;
import java.util.Date;
/**
 * @program: tx.framework
 * @description: 附件管理
 * @author:fyz
 * @create: 2018-07-09 13:22
 **/
public class File {

    private Integer id;
    private Integer post_id;
    private String file_type;
    private String file_path;
    private Integer file_status;
    private Integer post_status;
    private Integer file_num;



    public File(Integer id, Integer post_id, String file_type, String file_path, Integer file_status, Integer post_status) {
        this.id = id;
        this.post_id = post_id;
        this.file_type = file_type;
        this.file_path = file_path;
        this.file_status = file_status;
        this.post_status = post_status;
    }
    public File(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getFile_status() {
        return file_status;
    }

    public void setFile_status(Integer file_status) {
        this.file_status = file_status;
    }

    public Integer getPost_status() {
        return post_status;
    }

    public void setPost_status(Integer post_status) {
        this.post_status = post_status;
    }

    public Integer getFile_num() {
        return file_num;
    }

    public void setFile_num(Integer file_num) {
        this.file_num = file_num;
    }
}
