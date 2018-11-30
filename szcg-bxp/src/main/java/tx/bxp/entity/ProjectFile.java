package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:任务附件
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class ProjectFile {

    private Integer id;
    private Integer ProjCode;
    private Integer FileType;
    private Integer FileState;
    private Integer FilePath;
    private Date CreationTime;
    private String Memo;

    public ProjectFile() {
    }

    public ProjectFile(Integer id, Integer projCode, Integer fileType, Integer fileState, Integer filePath, Date creationTime, String memo) {
        this.id = id;
        ProjCode = projCode;
        FileType = fileType;
        FileState = fileState;
        FilePath = filePath;
        CreationTime = creationTime;
        Memo = memo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjCode() {
        return ProjCode;
    }

    public void setProjCode(Integer projCode) {
        ProjCode = projCode;
    }

    public Integer getFileType() {
        return FileType;
    }

    public void setFileType(Integer fileType) {
        FileType = fileType;
    }

    public Integer getFileState() {
        return FileState;
    }

    public void setFileState(Integer fileState) {
        FileState = fileState;
    }

    public Integer getFilePath() {
        return FilePath;
    }

    public void setFilePath(Integer filePath) {
        FilePath = filePath;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
