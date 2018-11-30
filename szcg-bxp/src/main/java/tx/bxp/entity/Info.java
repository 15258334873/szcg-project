package tx.bxp.entity;


    import java.util.Date;

    /**
     * @program: tx.framework
     * @description: 资讯管理
     * @author:fyz
     * @create: 2018-07-09 13:22
     **/

    public class Info {

        private  Integer id;
        private  Integer userid;
        private  String  title;
        private  String  content;
        private  Integer info_type;
        private  String  address;
        private  String  lat;
        private  String  lon;
        private  Date  creat_time;
        private  Integer isdel;
        private  String delperson;
        private  Date  delete_time;
        private  Integer Audit_status;
        private  String  auditor;
        private  Date  audit_time;

        public Info(Integer id, Integer userid, String title, String content, Integer info_type, String address, String lat, String lon, Date creat_time, Integer isdel, String delperson, Date delete_time, Integer audit_status, String auditor, Date audit_time) {
            this.id = id;
            this.userid = userid;
            this.title = title;
            this.content = content;
            this.info_type = info_type;
            this.address = address;
            this.lat = lat;
            this.lon = lon;
            this.creat_time = creat_time;
            this.isdel = isdel;
            this.delperson = delperson;
            this.delete_time = delete_time;
            this.Audit_status = audit_status;
            this.auditor = auditor;
            this.audit_time = audit_time;
        }

        public Info() {

        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserid() {
            return userid;
        }

        public void setUserid(Integer userid) {
            this.userid = userid;
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

        public Integer getInfo_type() {
            return info_type;
        }

        public void setInfo_type(Integer info_type) {
            this.info_type = info_type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public Date getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(Date creat_time) {
            this.creat_time = creat_time;
        }

        public Integer getIsdel() {
            return isdel;
        }

        public void setIsdel(Integer isdel) {
            this.isdel = isdel;
        }

        public String getDelperson() {
            return delperson;
        }

        public void setDelperson(String delperson) {
            this.delperson = delperson;
        }

        public Date getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(Date delete_time) {
            this.delete_time = delete_time;
        }

        public Integer getAudit_status() {
            return Audit_status;
        }

        public void setAudit_status(Integer audit_status) {
            Audit_status = audit_status;
        }

        public String getAuditor() {
            return auditor;
        }

        public void setAuditor(String auditor) {
            this.auditor = auditor;
        }

        public Date getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(Date audit_time) {
            this.audit_time = audit_time;
        }
    }

