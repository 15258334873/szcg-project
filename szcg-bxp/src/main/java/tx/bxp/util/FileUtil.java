package tx.bxp.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileUtil {
    public static String fileUp(MultipartFile file) {
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
            String filename = UUID.randomUUID().toString().replace("-", "").toUpperCase() + "fyzisgod";
            String newname = null;

            //图片
            if (extention.equals(".jpg")
                    || extention.equals(".bmp")
                    || extention.equals(".gif")
                    || extention.equals(".png")
                    || extention.equals(".jpeg")) {
                newname = filename + ".png";
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
            String path ="error";
            try {
                if(newname==null || newname.equals("")){
                }else{
                    String filePath = "E:/image/resources/upload/proj/" + newname;
                    // 转存文件
                    file.transferTo(new java.io.File(filePath));
                    path = "/resources/upload/proj/" + newname;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        } else {
            return "error";
        }
    }

    public static String photoUp(MultipartFile file,Integer id) {
        if (!file.isEmpty()) {
            String oldname = file.getOriginalFilename();
            // imgPath为原文件名
            int idx = oldname.lastIndexOf(".");
            // 文件后缀
            String extention = oldname.substring(idx);

            String filename = System.currentTimeMillis()+"_"+id;
            String newname = filename + ".png";;
            String path ="error";
            try {
                String filePath = "E:/image/resources/upload/photo/" + newname;
                // 转存文件
                file.transferTo(new java.io.File(filePath));
                return "/resources/upload/photo/" + newname;
            } catch (Exception e) {
               return  "error";
                //e.printStackTrace();
            }
        } else {
            return "error";
        }
    }

}
