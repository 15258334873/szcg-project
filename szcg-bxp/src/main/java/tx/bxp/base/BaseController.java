package tx.bxp.base;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 14:52
 **/
public class BaseController {
    /**
     * 获取cookie
     *
     * @param cookie
     * @param str
     * @return
     */
    public String getCookieValue(Cookie cookie, String str) {
        String value = cookie.getValue();
        try {
            value = URLDecoder.decode(value, "UTF-8");
            // String[] coo = value.split("\\|");
            // if(str.equals("userid")){
            // return coo[1];
            // }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }

    public String getSessionValue(HttpSession session, String name, String str){
        String [] arr = session.getAttribute(name).toString().split("\\|");
        if(str.equals("id")){
            return arr[2];
        }else if(str.equals("nickname")){
            return arr[0];
        }else if(str.equals("loginname")){
            return arr[1];
        }else{
            return "";
        }
    }

    public void updateSessionValue(HttpSession session,String name,String str){
        String newstr = session.getAttribute(name).toString()+"|"+str;
        session.setAttribute(name, newstr);
    }



    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @param filename
     * @return
     */
    public String fileupload(MultipartFile file, HttpServletRequest request,
                             String filename) {
        // 判断文件是否为空
        if (!file.isEmpty()) {

            String oldname = file.getOriginalFilename();

            // imgPath为原文件名
            int idx = oldname.lastIndexOf(".");
            // 文件后缀
            String extention = oldname.substring(idx);

            // java.util.Date dt = new java.util.Date(System
            // .currentTimeMillis());
            // SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
            // //新的文件名(日期+后缀)
            String newname =null;
            //图片
            if(extention.equals(".jpg")
                    || extention.equals(".bmp")
                    || extention.equals(".gif")
                    || extention.equals(".png")
                    || extention.equals(".jpge")) {
                newname = filename + extention;
            }
            //音频
            if(extention.equals(".cd")
                    || extention.equals(".wave")
                    || extention.equals(".aiff")
                    || extention.equals(".mpge")
                    || extention.equals(".mp3")
                    || extention.equals(".mpge-4")
                    || extention.equals(".midi")
                    || extention.equals(".wma")
                    || extention.equals(".aac")) {
                newname = filename + ".mp3";
            }

            //视频
            if(extention.equals(".avi")
                    || extention.equals(".mov")
                    || extention.equals(".asf")
                    || extention.equals(".mpge")
                    || extention.equals(".wmv")
                    || extention.equals(".3gp")
                    || extention.equals(".mkv")
                    || extention.equals(".flv")
                    || extention.equals(".rmvb")
                    || extention.equals(".rm")
                    || extention.equals(".mp4")
                    ) {
                newname = filename +".mp4";
            }

            try {

                String filePath ="E:/image/resources/upload/" + newname;
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/resources/upload/" + newname;
        } else {
            return "";
        }

    }

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @param filename
     * @return
     */
    public String fileupload(MultipartFile file, HttpServletRequest request,
                             String filename,String pathfile) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String oldname = file.getOriginalFilename();
            // imgPath为原文件名
            int idx = oldname.lastIndexOf(".");
            // 文件后缀
            String extention = oldname.substring(idx);

            //新的文件名(日期+后缀)
            String newname =null;
            try {
                if(pathfile.equals("photo")) {
                    newname=filename + extention;
                }else if(pathfile.equals("banner")) {
                    newname=filename + extention;
                }else if(pathfile.equals("2")) {
                    newname=filename + extention;
                }else if(pathfile.equals("apk")) {
                    newname=filename + extention;
                }else {
                    //图片
                    if(extention.equals(".jpg")
                            || extention.equals(".bmp")|| extention.equals(".gif")
                            || extention.equals(".png")|| extention.equals(".jpge")) {
                        newname =filename + extention;
                    }
                    //音频
                    if(extention.equals(".cd")
                            || extention.equals(".wave")
                            || extention.equals(".aiff")|| extention.equals(".mpge")
                            || extention.equals(".mp3")|| extention.equals(".mpge-4")
                            || extention.equals(".midi")|| extention.equals(".wma")
                            || extention.equals(".aac")) {
                        newname = filename + extention;
                    }
                    //视频
                    if(extention.equals(".avi")
                            || extention.equals(".mov")|| extention.equals(".asf")
                            || extention.equals(".mpge")|| extention.equals(".wmv")
                            || extention.equals(".3gp")|| extention.equals(".mkv")
                            || extention.equals(".flv")|| extention.equals(".rmvb")
                            || extention.equals(".rm")|| extention.equals(".mp4")
                            ) {
                        newname = filename + extention;
                    }
                }
                String filePath ="E:/image/resources/upload/" + pathfile+"/" +newname;
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/resources/upload/"+pathfile+"/" + newname;
        } else {
            return "";
        }
    }


    /**
     * 获取帖子编码
     *
     * @param code
     * @return
     */
    public String getCode(String code) {

        int a = Integer
                .parseInt(code.split("\\.")[code.split("\\.").length - 1]);

        String newcode = "" + (a + 1);

        for (int i = 0; i < 3; i++) {

            if (newcode.length() < 3) {

                newcode = "0" + newcode;
            }
        }
        newcode = code.substring(0, code.length() - 3) + newcode;
        return newcode;
    }

    /**
     * 获取分页开始和结束
     *
     * @param every
     * @param pagenum
     * @return
     */
    public int[] getPage(Integer every, Integer pagenum) {

        int beginIndex = 0;

        beginIndex = (pagenum - 1) * every;

        int endIndex = 0;

        endIndex = pagenum * every;
        int a[] = { beginIndex, endIndex };
        return a;

    }



    private void setSession(HttpServletRequest request) {

    }
}
