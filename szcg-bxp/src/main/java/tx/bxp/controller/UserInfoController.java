package tx.bxp.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.util.Password;
import tx.bxp.entity.Userinfo;
import tx.bxp.entity.Usermanage;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IUserinfoService;
import tx.bxp.service.IValidateService;
import tx.bxp.util.Base64Coder;
import tx.bxp.util.encoder.Md5PwdEncoder;
import tx.bxp.util.encoder.PwdEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户管理
 * @author FYZ
 * @date 2018-6-30
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IValidateService validateService;

    /**
     * 用户注册
     * @author FYZ
     * @date 2018/7/2 14:23
     * @param
     * @return
     */
    @PostMapping("/add")
    public Object insertUser(HttpServletRequest request)throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String Loginname=null;
        String password = null;
        String validate = null;
        String nickName = null;
        String version = null;
        String channelid=null;
        String flag =null;
        try {
            Loginname=request.getParameter("Loginname");
            password=request.getParameter("password");
            validate=request.getParameter("validate");
            nickName=request.getParameter("nickName");
            version=request.getParameter("version");
            channelid = request.getParameter("channelid");
            flag = request.getParameter("flag");

            if ((Loginname==null || "".equals(Loginname)) || (password==null || "".equals(password)) || (nickName==null || "".equals(nickName)) || (validate==null || "".equals(validate))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

            boolean LoginnameExist= userinfoService.isLoginnameExist(Loginname);
            if(LoginnameExist){
                throw BxpExceptionEnum.USER_EXIST.exception();
            }

            String Validate= validateService.getValidateByPhone(Loginname);
            if(Validate==null){
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if(!validate.equals(Validate)){
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
         /*   password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();*/

            Userinfo userinfo=new Userinfo();
            password = PwdEncoder.encodePassword(password);
            userinfo.setLoginname(Loginname);
            userinfo.setPassword(password);
            userinfo.setNickname(nickName);
            userinfo.setVersion(version);
            userinfo.setChannelid(channelid);
            userinfo.setFlag(flag);
            userinfo.setType("0");
            userinfoService.insertUserInfo(userinfo);
            userinfo.setPassword(null);
            return userinfo;

        }catch (BxpException e){
            throw e;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


    @RequestMapping(value={"/wechatRegister"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public Object wechatRegister(HttpServletRequest request, HttpServletResponse response, @RequestHeader String Authorization) throws BxpException {
        String nickname =null;
        String sex =null;
        String headimgurl =null;
        String unionid =null;
        String type = null;
        String version = null;
        try {
             nickname = request.getParameter("nickname");
             sex = request.getParameter("sex");
             headimgurl = request.getParameter("headimgurl");
             unionid = request.getParameter("unionid");
             type = request.getParameter("type");
             version = request.getParameter("version");

             if(nickname==null ||unionid==null){
                 throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
             }

            if (nickname.length() >= 5) {
                nickname = nickname.substring(0, 4) + "..";
            }
            if ("true".equals(sex)) {
                sex = "0";
            } else {
                sex = "1";
            }
            Integer id=userinfoService.getunionid(unionid);
             if(id==null || id.equals(0)){
                 //插入
                 Userinfo userinfo=new Userinfo();
                 userinfo.setNickname(nickname);
                 userinfo.setVersion(version);
                 userinfo.setPhoto(headimgurl);
                 userinfo.setUnionid(unionid);
                 userinfo.setType("1");
                 userinfoService.insertUserInfo(userinfo);
                 userinfo.setPassword(null);
                 return userinfo;
             }else{
                 //更新
                 Userinfo userinfo=new Userinfo();
                 userinfo.setNickname(nickname);
                 userinfo.setVersion(version);
                 userinfo.setPhoto(headimgurl);
                 userinfo.setUnionid(unionid);
                 userinfo.setType("1");
                 userinfo.setId(id);
                return  userinfoService.updateUser(userinfo);

             }
        }catch (BxpException e){
            throw e;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }

    }


    @RequestMapping(value={"/wechat_update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public Object wechatUpdate(HttpServletRequest request, HttpServletResponse response, @RequestHeader String Authorization) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();

        String uId = null;
        String phone = null;
        String validate = null;
        String password = null;
        String channelid = null;
        String flag = null;
        String unionid =null;
        try{
             uId = request.getParameter("uId");
             phone = request.getParameter("phone");
             validate = request.getParameter("validate");
             password = request.getParameter("password");
             channelid = request.getParameter("channelid");
             flag = request.getParameter("flag");
             unionid = request.getParameter("unionid");
            if(phone==null ||validate==null || password==null || uId==null ){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            boolean LoginnameExist= userinfoService.isLoginnameExist(phone);
            if(LoginnameExist){
                throw BxpExceptionEnum.USER_EXIST.exception();
            }

            String Validate= validateService.getValidateByPhone(phone);
            if(Validate==null){
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if(!validate.equals(Validate)){
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
          /*  password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();*/

            Userinfo userinfo=new Userinfo();
            password = PwdEncoder.encodePassword(password);
            userinfo.setLoginname(phone);
            userinfo.setPassword(password);
            userinfo.setId(Integer.valueOf(uId));
            userinfo.setChannelid(channelid);
            userinfo.setFlag(flag);
          return  userinfoService.updateUser(userinfo);


    }catch (BxpException e){
        throw e;
    }catch (Exception EX){
        throw BxpExceptionEnum.REQUEST_FEILED.exception();
    }
}

   @PostMapping("/login")
   public Userinfo login(HttpServletRequest request, HttpServletResponse response)throws BxpException{
        Userinfo userinfo=new Userinfo();
       Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
       String loginname =null;
       String password =null;
       String channelid =null;
       String flag =null;
       String version =null;
       String type =null;
       try{
           loginname = request.getParameter("username");
           password = request.getParameter("password");
           channelid = request.getParameter("channelid");
           flag = request.getParameter("flag");
           version = request.getParameter("version");
           type = request.getParameter("type");
           if ((loginname==null || "".equals(loginname)) || (password==null || "".equals(password))) {
               throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
           }
         /*  password = Base64Coder.decodeString(password);
           String[] passwords = password.split("[:]");
           password = passwords[0].toString();*/
           password = PwdEncoder.encodePassword(password);
           userinfo=userinfoService.login(loginname,password,flag,version,channelid);
           if( userinfo!=null){
               userinfo.setPassword(null);
               userinfo.setPaypassword(null);
               return userinfo;
           }else{
               throw BxpExceptionEnum.UNKNOWN_ACCOUNT.exception();
           }
       }catch (BxpException e){
           throw e;
       }catch (Exception EX){
           throw BxpExceptionEnum.REQUEST_FEILED.exception();
       }
   }

    /**
     * 更新用户信息
     * @author FYZ
     * @date 2018/7/2 14:23
     * @param
     * @return
     */
    @PostMapping("edit")
    public Object updateUser(Userinfo userinfo)throws BxpException{
        try {
            if(userinfo.getId()==null||"".equals(userinfo.getId())){
                throw  BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
           return userinfoService.updateUser(userinfo);
        }catch (BxpException E){
            throw E;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /** 头像上传
     * @author FYZ
     * @date 2018/7/4 14:23
     * @param
     * @return
     */
    @PostMapping("photoUp")
    public Object fileUp(@RequestParam("file") MultipartFile file,@Param("id")  Integer id)throws BxpException{
        try {
            if(id==null){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            boolean IdExist= userinfoService.isIdExist(id);
            if(!IdExist){
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
            return  userinfoService.photoUp(file,id);

        }catch (BxpException e){
            throw e;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

  /**
   * @author 修改用户登录密码
   * @date 2018/9/6 10:39
   * @param
   *
   * oldPassword 旧密码
   * Password   新密码
   *
   * @return
   */
    @PostMapping("updatepassword")
    public String updatePassword( @Param("id")  Integer id, @Param("oldPassword")  String oldPassword , @Param("Password")  String Password) throws BxpException{
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        try {
            if (( id==null) || (oldPassword==null || "".equals(oldPassword)) || (Password==null || "".equals(Password)) ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

              /*  Password = Base64Coder.decodeString(Password);
            String[] passwords = Password.split("[:]");
            Password = passwords[0].toString();

            oldPassword = Base64Coder.decodeString(oldPassword);
            passwords = oldPassword.split("[:]");
            oldPassword = passwords[0].toString();*/

            if(oldPassword.equals(Password)){
                throw BxpExceptionEnum.PASSWORD_IS_NAME.exception();
            }
           String  getpassword=userinfoService.getpassword(id);

            if(! PwdEncoder.encodePassword(oldPassword).equals(getpassword)){
                throw BxpExceptionEnum.PASSWORD_IS_WARONG.exception();
            }
            userinfoService.updatePassword(id,PwdEncoder.encodePassword(Password));
            return "登录密码更新成功";
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 修改用户支付密码
     * @author FYZ
     * @date 2018/7/4 14:23
     * @param
     *
     *  oldpayPassword 旧密码
     *  payPassword 新密码
     *
     * @return
     */
    @PostMapping("updatepaypassword")
    public String updatePaypassword(@Param("id")  Integer id, @Param("oldpayPassword")  String oldpayPassword , @Param("payPassword")  String payPassword) throws BxpException{
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        try {
            if (( id==null) || (oldpayPassword==null || "".equals(oldpayPassword)) || (payPassword==null || "".equals(payPassword)) ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

            /*  Password = Base64Coder.decodeString(Password);
            String[] passwords = Password.split("[:]");
            Password = passwords[0].toString();

            oldPassword = Base64Coder.decodeString(oldPassword);
            passwords = oldPassword.split("[:]");
            oldPassword = passwords[0].toString();*/

            if(oldpayPassword.equals(payPassword)){
                throw BxpExceptionEnum.PASSWORD_IS_NAME.exception();
            }
            String  getpaypassword=userinfoService.getpaypassword(id);
            if(! PwdEncoder.encodePassword(oldpayPassword).equals(getpaypassword)){
                throw BxpExceptionEnum.PASSWORD_IS_WARONG.exception();
            }
            userinfoService.updatePassword(id,PwdEncoder.encodePassword(payPassword));
            return "支付密码更新成功";
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 设置支付密码
     * @author chenmc
     * @date 2018/8/9 14:08
     * @param
     * @return
     */
    @PostMapping("setpaypassword")
    public String setpaypassword(@Param("id")  Integer id, @Param("payPassword")  String payPassword) throws BxpException{
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();

        try {
            if (( id==null) || (payPassword==null || "".equals(payPassword)) ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String  getpaypassword=userinfoService.getpaypassword(id);
            if(getpaypassword==null){

               /* payPassword = Base64Coder.decodeString(payPassword);
                String []  passwords = payPassword.split("[:]");
                payPassword = passwords[0].toString();*/

                payPassword=PwdEncoder.encodePassword(payPassword);

                userinfoService.updatePaypassword(id,payPassword);
            }else{
                throw BxpExceptionEnum.PAYPASSWORD_ALREEDY_SET.exception();
            }
            return "支付密码设置成功";
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 支付密码是否已设置
     * @author chenmc
     * @date 2018/8/9 14:08
     * @param
     * @return
     */
    @PostMapping("paypassword")
    public boolean paypassword(@Param("id") Integer id) throws BxpException{
        try {
            if (id==null ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String  getpaypassword=userinfoService.getpaypassword(id);
            if(getpaypassword==null || "".equals(getpaypassword)){
                throw BxpExceptionEnum.PAYPASSWORD_IS_SET.exception();
            }else{
                return true;
            }
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 重置支付密码
     * @author FYZ
     * @date 2018/8/9 14:08
     * @param
     * @return
     */
    @PostMapping("resetpaypassword")
    public String resetpaypassword(@Param("paypassword") String paypassword,@Param("loginname")  String loginname,@Param("validate") String validate) throws BxpException{
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        try {
            if ( loginname==null || paypassword==null || validate==null ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String Validate= validateService.getValidateByPhone(loginname);
            if(Validate==null){
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if(!validate.equals(Validate)){
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }

            Integer id=userinfoService.getID(loginname);
            if(id==null){
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }

               /*paypassword = Base64Coder.decodeString(paypassword);
                String []  passwords = paypassword.split("[:]");
                paypassword = passwords[0].toString();*/

            paypassword=PwdEncoder.encodePassword(paypassword);
            userinfoService.resetPaypassword(id,paypassword);

            return "支付密码重置成功";
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            System.out.println("paypassword");
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 重置登录密码
     * @author FYZ
     * @date 2018/8/9 14:08
     * @param
     * @return
     */
    @PostMapping("resetpassword")
    public String resetpassword(@Param("loginname")  String loginname,@Param("password") String password,@Param("validate") String validate) throws BxpException{
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        try {
            if ( loginname==null || password==null || validate==null ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String Validate= validateService.getValidateByPhone(loginname);
            if(Validate==null){
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if(!validate.equals(Validate)){
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
            Integer id=userinfoService.getID(loginname);
            if(id==null){
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
               /*paypassword = Base64Coder.decodeString(paypassword);
                String []  passwords = paypassword.split("[:]");
                paypassword = passwords[0].toString();*/

            password=PwdEncoder.encodePassword(password);
            userinfoService.resetPassword(id,password);
            return "登录密码重置成功";
        }catch (BxpException E){
            throw E;
        }catch ( Exception EX){
            System.out.println(password);
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


}