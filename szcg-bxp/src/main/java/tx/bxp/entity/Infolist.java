package tx.bxp.entity;

public class Infolist {
    private  String  title;
    private  String  content;
    private  String  file_path;
    private Integer  infotype;
    private  Integer browse_number;
    private  Integer like_number;
    private  String  videourl;
    private  String  imgurl;
    private  String audiourl;
   public  Infolist(){

    }

    public Infolist(String title, String content, String file_path, Integer infotype, Integer browse_number, Integer like_number, String videourl, String imgurl, String audiourl) {
        this.title = title;
        this.content = content;
        this.file_path = file_path;
        this.infotype = infotype;
        this.browse_number = browse_number;
        this.like_number = like_number;
        this.videourl = videourl;
        this.imgurl = imgurl;
        this.audiourl = audiourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getInfotype() {
        return infotype;
    }

    public void setInfotype(Integer infotype) {
        this.infotype = infotype;
    }

    public Integer getBrowse_number() {
        return browse_number;
    }

    public void setBrowse_number(Integer browse_number) {
        this.browse_number = browse_number;
    }

    public Integer getLike_number() {
        return like_number;
    }

    public void setLike_number(Integer like_number) {
        this.like_number = like_number;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }
}
