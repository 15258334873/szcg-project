package tx.bxp.service.Impl;

import javafx.beans.binding.ObjectBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import tx.bxp.entity.File;
import tx.bxp.entity.Userinfo;
import tx.bxp.mapper.UserinfoMapper;
import tx.bxp.service.IUserinfoService;
import tx.bxp.util.FileUtil;
import tx.commons.util.FileUploadUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-06-28 14:00
 **/


@Service
@Transactional
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Value("${bxp.imgurl}")
    private String imgurl;

    @Value("${bxp.imgurl1}")
    private String imgurl1;

    //用户插入
    @Override
    public void insertUserInfo(Userinfo userinfo) {
        userinfoMapper.insertUserInfo(userinfo);
    }

    //  用户信息更新
    @Override
    public Userinfo updateUser(Userinfo userinfo) {
        userinfoMapper.updateUser(userinfo);
       Userinfo userinfo1= userinfoMapper.SelectUserById(userinfo.getId());
       if(userinfo1!=null){
           if(userinfo1.getPhoto()!=null){
               if(userinfo1.getPhoto().indexOf("http")==-1){
                   userinfo1.setPhoto(imgurl1+userinfo1.getPhoto());
               }
           }
       }
        return userinfo1;
    }

    //修改登录密码
    @Override
    public void updatePassword(Integer id, String password) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setPassword(password);
        userinfoMapper.updateUser(userinfo);
    }

    //修改支付密码
    @Override
    public void updatePaypassword(Integer id, String paypassword) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setPaypassword(paypassword);
        userinfoMapper.updateUser(userinfo);
    }

    // 用户头像上传
    @Override
    public Userinfo photoUp(MultipartFile file, Integer id) throws Exception {
        List<String> fileList = null;
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        try {
            fileList = FileUploadUtils.uploadFile(imgurl, "/bxp/photo", file);
            if (fileList != null) {
                userinfo.setPhoto(fileList.get(0));
                userinfoMapper.updateUser(userinfo);
            }else{
                throw  new  Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Userinfo userinfo1= userinfoMapper.SelectUserById(userinfo.getId());
        if(userinfo1!=null){
            if(userinfo1.getPhoto()!=null){
                if(userinfo1.getPhoto().indexOf("http")==-1){
                    userinfo1.setPhoto(imgurl1+userinfo1.getPhoto());
                }
            }
        }
        return userinfo1;
    }

    //重置登录密码
    @Override
    public void resetPassword(Integer id, String password) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setPassword(password);
        userinfoMapper.updateUser(userinfo);
    }

    //重置支付密码
    @Override
    public void resetPaypassword(Integer id, String paypassword) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setPaypassword(paypassword);
        userinfoMapper.updateUser(userinfo);
    }

    @Override
    public Userinfo selectUserinfoBynickname(String nickname) {
        return null;
    }

    @Override
    public Userinfo selectUserinfoByID(int id) {
        Userinfo userinfo1= userinfoMapper.SelectUserById(id);
        if(userinfo1!=null){
            if(userinfo1.getPhoto()!=null){
                if(userinfo1.getPhoto().indexOf("http")==-1){
                    userinfo1.setPhoto(imgurl1+userinfo1.getPhoto());
                }
            }
        }
        return userinfo1;
       // return userinfoMapper.SelectUserById(id);
    }

    @Override
    public boolean isLoginnameExist(String LoginName) {
        String loginname = userinfoMapper.SelectUserInfoByLoginName(LoginName);
        if (loginname == null || "".equals(loginname)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isIdExist(Integer Id) {
        Integer id = userinfoMapper.SelectUserInfoById(Id);
        if (id == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getpassword(Integer Id) {
        return userinfoMapper.SelectpasswordById(Id);
    }

    @Override
    public String getpaypassword(Integer Id) {
        return userinfoMapper.SelectpaypasswordById(Id);
    }

    @Override
    public Integer getID(String loginname) {
        return userinfoMapper.SelectidByloginname(loginname);
    }

    @Override
    public Userinfo login(String phone, String password, String flag, String version, String channelid) {
        Userinfo userinfo = new Userinfo();
        userinfo.setLoginname(phone);
        userinfo.setPassword(password);
        userinfo = userinfoMapper.login(userinfo);
        if (userinfo != null) {
            if(userinfo.getPhoto()==null){
                if (flag == null && version == null && channelid == null) {

                } else {
                    Userinfo user = new Userinfo();
                    user.setFlag(flag);
                    user.setVersion(version);
                    user.setChannelid(channelid);
                    user.setId(userinfo.getId());
                    userinfoMapper.updateUser(user);
                }
            }else{
              if(userinfo.getPhoto().indexOf("http")==-1){
                  userinfo.setPhoto(imgurl1+userinfo.getPhoto());
              }
            }
        }
        return userinfo;
    }

    @Override
    public Integer getunionid(String unionid) {
        return userinfoMapper.SelectUserByUnionid(unionid);
    }

    @Override
    public boolean selectUserinfoByIDandPay(Integer id, String paypassword) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("id", id);
        map.put("paypassword", paypassword);
        Userinfo userinfo = userinfoMapper.selectUserinfoByIDandPay(map);
        if (userinfo == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userinfoMapper.deleteUser(id);
    }


    @Override
    public Userinfo selectUserByLoginname(String cellNum) {
        Userinfo userinfo= userinfoMapper.selectUserByLoginname(cellNum);
        return userinfoMapper.selectUserByLoginname(cellNum);
    }

    @Override
    public void updateUser1(Userinfo userinfo) {
        userinfoMapper.updateUser(userinfo);
        ;
    }
}


