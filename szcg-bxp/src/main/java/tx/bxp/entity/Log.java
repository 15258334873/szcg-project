package tx.bxp.entity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.util.BaseUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Log {
    private Integer id;
    private Integer userid;
    private Integer projcode;
    private String why;

    public Log() {
    }

    public Log(Integer id, Integer userid, Integer projcode, String why) {
        this.id = id;
        this.userid = userid;
        this.projcode = projcode;
        this.why = why;
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

    public Integer getProjcode() {
        return projcode;
    }

    public void setProjcode(Integer projcode) {
        this.projcode = projcode;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    @RestController
    @RequestMapping({ "/proj" })
    public static class ProjectController  {

        String url = "http://192.168.251.51:8887/InterFaceService.asmx";


        /**
         * 案件上报前验证接口
         * @param request
         * @param Authorization
         * @return
         */
        @ResponseBody
        @RequestMapping(value = { "/Validate" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
        public void Validate(HttpServletRequest request, @RequestHeader String Authorization) {

        }


        /**
         * 案件上报（我要举报）
         *
         * @param request

         * @param files
         * @return
         * 	@RequestHeader(required = false, value = "Authorization") String Authorization,
         */
        @RequestMapping(value = { "/report" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
        public void report(HttpServletRequest request,@RequestParam(value = "projfile", required = false) MultipartFile[] files){

            String UserId=null;
            String address = null;
            String lon = null;
            String lat = null;
            String title = "";
            String mome = null;
            try{
                address = request.getParameter("address");
                UserId=request.getParameter("UserId");
                lon=request.getParameter("lon");
                lat=request.getParameter("lat");
                mome=request.getParameter("mome");

                String fff=BaseUtil.fileupload(files[0], request,"1");


                //if()




            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
