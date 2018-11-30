package tx.bxp.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * @program: szcg-project
 * @description: 工具类
 * @author:pck
 * @create: 2018-07-11 10:08
 **/
public class BaseUtil {

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @param filename
     * @return
     */
    public static String fileupload(MultipartFile file, HttpServletRequest request,String filename) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String oldname = file.getOriginalFilename();
            // imgPath为原文件名
            int idx = oldname.lastIndexOf(".");
            // 文件后缀
            String extention = oldname.substring(idx);
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
                String filePath ="E:/image/resources/" + newname;
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

}
