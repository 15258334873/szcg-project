package tx.bxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.models.auth.In;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.returnObj;
import tx.bxp.entity.*;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.*;
import tx.bxp.util.BankUtils;
import tx.bxp.util.Base64Coder;
import tx.bxp.util.ReturnMsg;
import tx.bxp.util.encoder.Md5PwdEncoder;
import tx.bxp.util.encoder.PwdEncoder;
import tx.framework.web.annotation.OriginalJson;
import tx.framework.web.json.bxp.ReturnObj;

/**
 * 手机APP调用类
 * @author pck
 */

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IValidateService validateService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IVersionService VersionService;

    @Autowired
    private IbxpProjAwardService projAwardService;

    @Autowired
    private IBannerService BannerService;

    @Autowired
    private IBankCardService bankCardService;

    @Autowired
    private IBindNoteService bindNoteService;

    @Autowired
    private ITradingnoteService tradingnoteService;

    @Autowired
    private IWechatService wechatService;

    //银行卡调用地址
    @Value("${bxp.BackpurseUrl}")
    private String purseUrl;

    //老版数据调用地址
    @Value("${bxp.DataUrl}")
    private String DataUrl;

    /**
     * 获取验证码
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/getValidate")
    public ReturnObj getValidate(HttpServletRequest request) throws BxpException {
        String Phone = null;
        StringBuffer validate = new StringBuffer();
        try {
            Phone = request.getParameter("phone");
            if (Phone == null || "".equals(Phone)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            //生成码证码
            Random d = new Random();
            for (int i = 0; i < 6; i++) {
                validate = validate.append(d.nextInt(10));
            }
            boolean isValidatePhone = validateService.isValidatePhone(Phone);
            if (isValidatePhone) {
                //更新
                validateService.editValidate(Phone, validate.toString());
            } else {
                //插入
                validateService.addValidate(Phone, validate.toString());
            }
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("验证码获取成功");
            returnObj.setObj(null);
            return returnObj;

        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 重新获验证码
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/19 14:44
     */
    @PostMapping("/regainValidate")
    public ReturnObj RegainValidate(HttpServletRequest request) throws BxpException {
        String Phone = null;
        StringBuffer validate = new StringBuffer();
        try {
            Phone = request.getParameter("phone");
            if (Phone == null || "".equals(Phone)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            //生成码证码
            Random d = new Random();
            for (int i = 0; i < 6; i++) {
                validate = validate.append(d.nextInt(10));
            }
            boolean isValidatePhone = validateService.isValidatePhone(Phone);
            if (isValidatePhone) {
                //更新
                validateService.editValidate(Phone, validate.toString());
            } else {
                //插入
                validateService.addValidate(Phone, validate.toString());
            }
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("验证码获取成功");
            returnObj.setObj(null);
            return returnObj;

        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 用户注册
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @PostMapping("/user_Register")
    public ReturnObj insertUser(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String Loginname = null;
        String password = null;
        String validate = null;
        String nickName = null;
        String version = null;
        String channelid = null;
        String flag = null;
        try {
            Loginname = request.getParameter("username");
            password = request.getParameter("password");
            validate = request.getParameter("validate");
            nickName = request.getParameter("nickName");
            version = request.getParameter("version");
            channelid = request.getParameter("channelid");
            flag = request.getParameter("flag");
            if ((Loginname == null || "".equals(Loginname)) || (password == null || "".equals(password)) || (nickName == null || "".equals(nickName)) || (validate == null || "".equals(validate))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            boolean LoginnameExist = userinfoService.isLoginnameExist(Loginname);
            if (LoginnameExist) {
                throw BxpExceptionEnum.USER_EXIST.exception();
            }
            String Validate = validateService.getValidateByPhone(Loginname);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
            password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();
            Userinfo userinfo = new Userinfo();
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
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("用户注册成功");
            returnObj.setObj(userinfo);
            return returnObj;

        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     *更新极光推送的Tag
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @PostMapping(value = "/edit_tag")
    public ReturnObj edit_tag(HttpServletRequest request, HttpServletResponse response, @RequestHeader String Authorization) throws BxpException {

        String id = null;
        String channelid = null;

        try {
            id = request.getParameter("id");
            channelid = request.getParameter("channelid");
            if (id == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
                //更新
                Userinfo userinfo = new Userinfo();
                userinfo.setChannelid(channelid);
                userinfo.setId(Integer.valueOf(id));
               userinfoService.updateUser1(userinfo);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setFlag("用户信息更新成功");
                returnObj.setObj(null);
                return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 第三方用户注册
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @PostMapping(value = "/wechat_Register")
    public ReturnObj wechatRegister(HttpServletRequest request, HttpServletResponse response, @RequestHeader String Authorization) throws BxpException {

        String nickname = null;
        String sex = null;
        String headimgurl = null;
        String unionid = null;
        String type = null;
        String version = null;
        try {
            nickname = request.getParameter("nickname");
            sex = request.getParameter("sex");
            headimgurl = request.getParameter("headimgurl");
            unionid = request.getParameter("unionid");
            type = request.getParameter("type");
            version = request.getParameter("version");

            if (nickname == null || unionid == null) {
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
            Integer id = userinfoService.getunionid(unionid);
            if (id == null || id.equals(0)) {
                //插入
                Userinfo userinfo = new Userinfo();
                userinfo.setNickname(nickname);
                userinfo.setVersion(version);
                userinfo.setPhoto(headimgurl);
                userinfo.setUnionid(unionid);
                userinfo.setType("1");
                userinfoService.insertUserInfo(userinfo);
                userinfo.setPassword(null);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setFlag("微信用户注册成功");
                returnObj.setObj(userinfo);
                return returnObj;
            } else {
                //更新
                Userinfo userinfo = new Userinfo();
                userinfo.setNickname(nickname);
                userinfo.setVersion(version);
                userinfo.setPhoto(headimgurl);
                userinfo.setUnionid(unionid);
                userinfo.setType("1");
                userinfo.setId(id);
                userinfo = userinfoService.updateUser(userinfo);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setFlag("微信用户注册成功");
                returnObj.setObj(userinfo);
                return returnObj;
            }
        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 第三方用户注册后，更新信息
     * @param
     * @return
     * @author
     * @date 2018/9/7 10:33
     */
    @PostMapping(value = "/wechat_update")
    public ReturnObj wechatUpdate(HttpServletRequest request, HttpServletResponse response, @RequestHeader String Authorization) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String uId = null;
        String phone = null;
        String validate = null;
        String password = null;
        String channelid = null;
        String flag = null;
        String unionid = null;
        try {
            uId = request.getParameter("uId");
            phone = request.getParameter("phone");
            validate = request.getParameter("validate");
            password = request.getParameter("password");
            channelid = request.getParameter("channelid");
            flag = request.getParameter("flag");
            unionid = request.getParameter("unionid");
            if (phone == null || validate == null || password == null || uId == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            boolean LoginnameExist = userinfoService.isLoginnameExist(phone);
            if (LoginnameExist) {
                throw BxpExceptionEnum.USER_EXIST.exception();
            }

            String Validate = validateService.getValidateByPhone(phone);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
            password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();

            Userinfo userinfo = new Userinfo();
            password = PwdEncoder.encodePassword(password);
            userinfo.setLoginname(phone);
            userinfo.setPassword(password);
            userinfo.setId(Integer.valueOf(uId));
            userinfo.setChannelid(channelid);
            userinfo.setFlag(flag);
            userinfo = userinfoService.updateUser(userinfo);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("微信用户信息更新成功");
            returnObj.setObj(userinfo);
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 用户登录
     * @param
     * @return
     * @author
     * @date 2018/9/7 10:35
     */
    @PostMapping("/user_Login")
    public ReturnObj login(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        Userinfo userinfo = new Userinfo();
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String loginname = null;
        String password = null;
        String channelid = null;
        String flag = null;
        String version = null;
        String type = null;
        try {
            loginname = request.getParameter("username");
            password = request.getParameter("password");
            channelid = request.getParameter("channelid");
            flag = request.getParameter("flag");
            version = request.getParameter("version");
            type = request.getParameter("type");
            if ((loginname == null || "".equals(loginname)) || (password == null || "".equals(password))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();
            password = PwdEncoder.encodePassword(password);

            userinfo = userinfoService.login(loginname, password, flag, version, channelid);
            if (userinfo != null) {
                userinfo.setPassword(null);
                userinfo.setPaypassword(null);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setFlag("用户登录成功");
                returnObj.setObj(userinfo);
                return returnObj;
            } else {
                throw BxpExceptionEnum.UNKNOWN_ACCOUNT.exception();
            }
        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 更新用户信息
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @PostMapping("user_update")
    public ReturnObj updateUser(HttpServletRequest request) throws BxpException {
        String id = request.getParameter("userId");
        String sex = request.getParameter("sex");
        String nickname = request.getParameter("nickname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        try {
            Userinfo userinfo = new Userinfo();
            userinfo.setId(Integer.valueOf(id));
            userinfo.setAddress(address);
            userinfo.setNickname(nickname);
            userinfo.setEmail(email);

            if (userinfo.getId() == null || "".equals(userinfo.getId())) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (sex.equals("true")) {
                userinfo.setSex(0);
            } else {
                userinfo.setSex(1);
            }
            Userinfo userinfo1 = userinfoService.updateUser(userinfo);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("用户信息更新成功");
            returnObj.setObj(userinfo1);
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 修改用户登录密码
     * @return
     * @author
     * @date 2018/9/6 10:39
     */
    @PostMapping("change_password")
    public ReturnObj updatePassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String id = null;
        String oldPassword = null;
        String Password = null;
        try {
            id = request.getParameter("userID");
            oldPassword = request.getParameter("oldpassword");
            Password = request.getParameter("newpassword");
            if ((id == null) || (oldPassword == null || "".equals(oldPassword)) || (Password == null || "".equals(Password))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Password = Base64Coder.decodeString(Password);
            String[] passwords = Password.split("[:]");
            Password = passwords[0].toString();
            oldPassword = Base64Coder.decodeString(oldPassword);
            passwords = oldPassword.split("[:]");
            oldPassword = passwords[0].toString();
            if (oldPassword.equals(Password)) {
                throw BxpExceptionEnum.PASSWORD_IS_NAME.exception();
            }
            String getpassword = userinfoService.getpassword(Integer.valueOf(id));
            String sss = PwdEncoder.encodePassword(oldPassword);

            if (!sss.equals(getpassword)) {
                throw BxpExceptionEnum.PASSWORD_IS_WARONG.exception();
            }
            System.out.println(PwdEncoder.encodePassword(Password));
            userinfoService.updatePassword(Integer.valueOf(id), PwdEncoder.encodePassword(Password));
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("登录密码更新成功");
            returnObj.setObj(null);
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 修改用户支付密码
     * @return
     * @author FYZ
     * @date 2018/7/4 14:23
     */
    @PostMapping("change_paypassword")
    public ReturnObj updatePaypassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String id = null;
        String oldpayPassword = null;
        String payPassword = null;
        try {
            id = request.getParameter("userID");
            oldpayPassword = request.getParameter("oldpassword");
            payPassword = request.getParameter("newpassword");

            if ((id == null) || (oldpayPassword == null || "".equals(oldpayPassword)) || (payPassword == null || "".equals(payPassword))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            payPassword = Base64Coder.decodeString(payPassword);
            String[] passwords = payPassword.split("[:]");
            payPassword = passwords[0].toString();
            oldpayPassword = Base64Coder.decodeString(oldpayPassword);
            passwords = oldpayPassword.split("[:]");
            oldpayPassword = passwords[0].toString();
            if (oldpayPassword.equals(payPassword)) {
                throw BxpExceptionEnum.PASSWORD_IS_NAME.exception();
            }
            String getpaypassword = userinfoService.getpaypassword(Integer.valueOf(id));
            if (!PwdEncoder.encodePassword(oldpayPassword).equals(getpaypassword)) {
                throw BxpExceptionEnum.PASSWORD_IS_WARONG.exception();
            }
            userinfoService.updatePaypassword(Integer.valueOf(id), PwdEncoder.encodePassword(payPassword));
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("支付密码更新成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 头像上传
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/4 14:23
     */
    @PostMapping("updateUserImg")
    public ReturnObj fileUp(@RequestParam("uploadFile") MultipartFile file, HttpServletRequest request) throws BxpException {
        String userid = request.getParameter("userid");
        Integer id = new Integer(userid);
        try {
            if (id == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            boolean IdExist = userinfoService.isIdExist(id);
            if (!IdExist) {
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
            Userinfo userinfo = userinfoService.photoUp(file, id);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setFlag("头像更新成功");
            returnObj.setObj(userinfo);
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 重置登录密码
     * @param
     * @return
     * @author FYZ
     * @date 2018/8/9 14:08
     */
    @PostMapping("retrievepassword")
    public ReturnObj resetpassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String loginname = null;
        String password = null;
        String validate = null;
        try {
            loginname = request.getParameter("username");
            password = request.getParameter("password");
            validate = request.getParameter("validate");
            if (loginname == null || password == null || validate == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String Validate = validateService.getValidateByPhone(loginname);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
            Integer id = userinfoService.getID(loginname);
            if (id == null) {
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
            password = Base64Coder.decodeString(password);
            String[] passwords = password.split("[:]");
            password = passwords[0].toString();
            password = PwdEncoder.encodePassword(password);
            userinfoService.resetPassword(id, password);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("登录密码重置成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            System.out.println(password);
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 重置支付密码
     * @param
     * @return
     * @author FYZ
     * @date 2018/8/9 14:08
     */
    @PostMapping("retrievepaypassword")
    public ReturnObj resetpaypassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String loginname = null;
        String paypassword = null;
        String validate = null;
        try {
            loginname = request.getParameter("username");
            paypassword = request.getParameter("password");
            validate = request.getParameter("validate");
            if (loginname == null || paypassword == null || validate == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String Validate = validateService.getValidateByPhone(loginname);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
            Integer id = userinfoService.getID(loginname);
            if (id == null) {
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
            paypassword = Base64Coder.decodeString(paypassword);
            String[] passwords = paypassword.split("[:]");
            paypassword = passwords[0].toString();
            paypassword = PwdEncoder.encodePassword(paypassword);
            userinfoService.resetPaypassword(id, paypassword);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("支付密码重置成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            System.out.println("paypassword");
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 设置支付密码
     * @param
     * @return
     * @author chenmc
     * @date 2018/8/9 14:08
     */
    @PostMapping("setpaypassword")
    public ReturnObj setpaypassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String payPassword = null;
        String id = null;
        try {
            id = request.getParameter("userID");
            payPassword = request.getParameter("password");
            if ((id == null) || (payPassword == null || "".equals(payPassword))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String getpaypassword = userinfoService.getpaypassword(Integer.valueOf(id));
            if (getpaypassword == null) {
                payPassword = Base64Coder.decodeString(payPassword);
                String[] passwords = payPassword.split("[:]");
                payPassword = passwords[0].toString();
                payPassword = PwdEncoder.encodePassword(payPassword);
                userinfoService.updatePaypassword(Integer.valueOf(id), payPassword);
            } else {
                throw BxpExceptionEnum.PAYPASSWORD_ALREEDY_SET.exception();
            }
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("支付密码设置成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 获取用户信息
     * @param
     * @return
     * @author
     * @date 11:21
     */
    @PostMapping("getuser")
    public ReturnObj getuser(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String userID = null;
        try {
            userID = request.getParameter("userID");
            if (userID == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Integer i = Integer.valueOf(userID);
            Userinfo userinfo = userinfoService.selectUserinfoByID(Integer.valueOf(userID));
            if (userinfo == null) {
                throw BxpExceptionEnum.USER_NO_EXIST.exception();
            } else {
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(userinfo);
                returnObj.setFlag("获取用户信息成功");
                return returnObj;
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 验证支付密码是否正确
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 13:44
     */
    @PostMapping("Verificationpaypassword")
    public ReturnObj Verificationpaypassword(HttpServletRequest request) throws BxpException {
        String id = null;
        String paypassword = null;
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        try {
            id = request.getParameter("userID");
            paypassword = request.getParameter("paypassword");
            if ((id == null) || (paypassword == null || "".equals(paypassword))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            paypassword = Base64Coder.decodeString(paypassword);
            String[]  paypasswords= paypassword.split("[:]");
            paypassword = paypasswords[0].toString();
            paypassword = PwdEncoder.encodePassword(paypassword);

            boolean userinfo = userinfoService.selectUserinfoByIDandPay(Integer.valueOf(id), paypassword);
            if (userinfo) {
                ReturnObj returnObj = new ReturnObj();
                returnObj.setFlag("支付密码正确");
                returnObj.setObj(null);
                return returnObj;
            } else {
                throw BxpExceptionEnum.PAY_PASSWORD_IS_NAME.exception();
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 用户退出
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 13:24
     */
    @PostMapping("deleteUser")
    public ReturnObj deleteUser(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String id = null;
        try {
            id = request.getParameter("userId");
            if ((id == null)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            userinfoService.deleteUser(Integer.valueOf(id));
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("退出成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 检查支付密码
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 13:44
     */
    @PostMapping("getpaypassword")
    public ReturnObj getpaypassword(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String id = null;
        try {
            id = request.getParameter("userId");
            if ((id == null)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String getpaypassword = userinfoService.getpaypassword(Integer.valueOf(id));
            if (getpaypassword == null) {
                throw BxpExceptionEnum.PAYPASSWORD_IS_SET.exception();
            } else {
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(null);
                returnObj.setFlag("支付密码已设置");
                return returnObj;
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 个人奖励总和
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 13:59
     */
    @PostMapping("myaccount")
    public ReturnObj myaccount(HttpServletRequest request) throws BxpException {
        Md5PwdEncoder PwdEncoder = new Md5PwdEncoder();
        String id = null;
        String Cumulative="0";
        Integer integral=0;
        String Noreceive="0";
        try {
            id = request.getParameter("userId");
            if ((id == null)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            tx.bxp.dto.ProjectAward projectAward = projAwardService.getaccount(Integer.valueOf(id));
            //老数据开始
            HttpClient httpClient = new DefaultHttpClient();
            String url =DataUrl+"/mobile/myaccount?userId="+id;

            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);
            int statusCode = resp.getStatusLine().getStatusCode();
            if(statusCode==200){
                BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity()
                        .getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                String content = sb.toString();

                JSONObject jsonObject = JSON.parseObject(content);
                String returnFlag=(String) jsonObject.get("returnFlag");

                if(returnFlag==null || returnFlag.equals("1")){
                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                }else{
                    Cumulative=jsonObject.getJSONObject("returnObj").get("Cumulative").toString();
                    Noreceive=jsonObject.getJSONObject("returnObj").get("Noreceive").toString();
                    integral=jsonObject.getJSONObject("returnObj").getInteger("integral");
                }
            }else{
                throw BxpExceptionEnum.REQUEST_FEILED.exception();
            }
            //老数据结束
            projectAward.setNoreceive(projectAward.getNoreceive().add(new BigDecimal(Noreceive.toString())));
            projectAward.setCumulative(projectAward.getCumulative().add(new BigDecimal(Cumulative.toString())));
            projectAward.setIntegral(projectAward.getIntegral()+integral);
            ReturnObj returnObj = new ReturnObj();

            returnObj.setObj(projectAward);
            returnObj.setFlag("个人奖励");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * APP版本更新
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 15:20
     */
    @PostMapping("getappversion")
    public ReturnObj getappversion(HttpServletRequest request) throws BxpException {
        try {
            Integer userid = Integer.valueOf(request.getParameter("uId"));
            String version = request.getParameter("version");
            if (userid != null) {
                Userinfo userinfo = new Userinfo();
                userinfo.setId(userid);
                userinfo.setVersion(version);
                userinfoService.updateUser(userinfo);
            }
            if (version == null || version.length() == 0) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            AppVersion appVersion = VersionService.getAppVersion();
            String Versioncode = appVersion.getVersioncode();
            if (Versioncode.equals(version)) {
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(null);
                returnObj.setFlag("不需要更新版本");
                return returnObj;
            } else {
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(appVersion);
                returnObj.setFlag("发现新的版本");
                return returnObj;
            }
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 轮播图
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 16:47
     */
    @PostMapping("getbanner")
    public ReturnObj getbanner(HttpServletRequest request) throws BxpException {
        try {
            List<Banner> banners = BannerService.BannerList();
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(banners);
            returnObj.setFlag("获取轮播图成功");
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    //银行卡功能

    /**
     * 银行卡绑定
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/17 9:45
     */
    @PostMapping("binding")
    public ReturnObj binding(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        String CellNum = null;//用户名(手机号)
        String type = null;//类型（0 ：银行 1：微信）
        String BankNum = null;//银行卡号
        String validate = null;//验证码
        String userId=null;//用户Id
        java.util.Date date = new java.util.Date();
        try {
            CellNum = request.getParameter("username");
            type = request.getParameter("BindType");
            BankNum = request.getParameter("BankNum");
            validate = request.getParameter("validate");
            userId=request.getParameter("userId");
         if ((userId == null || "".equals(userId)) || (CellNum == null || "".equals(CellNum)) || (type == null || "".equals(type)) || (BankNum == null || "".equals(BankNum)) || (validate == null || "".equals(validate))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
           String Validate = validateService.getValidateByPhone(CellNum);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }

            BindNote bindNote = bankUtils.getBindNote("中国银行", date, 0, BankNum, 0, CellNum);
            bindNote.setUserid(Integer.valueOf(userId));
            Integer BindType = Integer.valueOf(type);
            BankCard bankCard = new BankCard();
            bankCard.setUsername(CellNum);
            bankCard.setBanknum(BankNum);
            bankCard.setBankname("中国银行");
            bankCard.setCudate(date);
            bankCard.setUserid(Integer.valueOf(userId));
            Integer b = this.bankCardService.selectBanknum(CellNum);

            if (b != null && b >= 1) {
                bindNote.setStatus(Integer.valueOf(-30));
                bindNote.setMsg("该银行卡已被绑定");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.BANK_BINDUNG.exception();
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(date);
            Integer a = Integer.valueOf(time.indexOf(":"));
            String st = time.substring(a.intValue() - 2, a.intValue());
            Integer hour = Integer.valueOf(st);
            if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.DATE_ERROR.exception();
            }

            HttpClient httpClient = new DefaultHttpClient();
            String url = this.purseUrl + "/bonus/banks/acceptAddUserBinding?CellNum=" + CellNum +
                    "&BindType=" + BindType + "&BankNum=" + BankNum;
            System.out.println(url);
            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);

            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                List<String> lists = bankUtils.getResponseValues(resp);
                bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                bindNote.setMsg((String) lists.get(0));
                if (((String) lists.get(1)).equals("00")) {
                    this.bankCardService.binding(bindNote, bankCard);
                    returnObj.setObj(null);
                    returnObj.setFlag((String) lists.get(0));
                } else {
                    this.bindNoteService.insertBindNote(bindNote);
                    throw new BxpException(-1, (String) lists.get(0));
                }
            } else {
                bindNote.setStatus(Integer.valueOf(-32));
                bindNote.setMsg("获取银行数据失败，请稍后再试");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.BANK_ERROR.exception();
            }
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 银行卡解绑
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/17 17:51
     */
    @PostMapping("unBinding")
    public ReturnObj unBinding(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        java.util.Date date = new java.util.Date();

        String CellNum = null;
        String BindType = null;
        String BankNum = null;
        String validate = null;

        try {
            CellNum = request.getParameter("username");
            BindType = request.getParameter("BindType");
            BankNum = request.getParameter("BankNum");
            validate = request.getParameter("validate");
           if ( (BankNum == null || "".equals(BankNum))  || (CellNum == null || "".equals(CellNum))  || (BindType == null || "".equals(BindType)) || (validate == null || "".equals(validate))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
         /*   String Validate = validateService.getValidateByPhone(CellNum);
            if (Validate == null) {
                throw BxpExceptionEnum.VALIDATE_PAST.exception();
            }
            if (!validate.equals(Validate)) {
                throw BxpExceptionEnum.VALIDATE_MISTAKE.exception();
            }
*/
            BindNote bindNote = bankUtils.getBindNote("中国银行", date, 1, "0", 0, CellNum);
            Integer b = this.bankCardService.selectBanknum(BankNum);
            if (b == null || b.equals(0)) {
                bindNote.setStatus(Integer.valueOf(-33));
                bindNote.setMsg("您未绑定银行卡,无需解绑");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.NOT_BANK.exception();
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(date);
            Integer a = Integer.valueOf(time.indexOf(":"));
            String st = time.substring(a.intValue() - 2, a.intValue());
            Integer hour = Integer.valueOf(st);
            if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.DATE_ERROR.exception();
            }

            Boolean b1 = userinfoService.isLoginnameExist(CellNum);
            if (!b1) {
                bindNote.setStatus(Integer.valueOf(-51));
                bindNote.setMsg("没有此用户，不能解绑");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.NOT_USER_BANK.exception();
            }

            HashMap<String, Object> maps = new HashMap();
            maps.put("username", CellNum);
            maps.put("type", BindType);

            Integer l = this.tradingnoteService.selectTradingnoteByUsernameAndType(maps);
            if (l.intValue() >= 1) {
                bindNote.setStatus(Integer.valueOf(-50));
                bindNote.setMsg("您有奖励正在提现中,不能解绑");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.NOT_USER_AWARD.exception();
            }

            HttpClient httpClient = new DefaultHttpClient();
            String url = this.purseUrl + "/bonus/banks/unbundling?CellNum=" + CellNum + "&Type=" + BindType;
            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                List<String> lists = bankUtils.getResponseValues(resp);
                bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                bindNote.setMsg((String) lists.get(0));
                if (((String) lists.get(1)).equals("00")) {
                    this.bankCardService.unBinding(bindNote, CellNum);
                    returnObj.setObj(null);
                    returnObj.setFlag((String) lists.get(0));
                } else {
                    this.bindNoteService.insertBindNote(bindNote);
                    throw new BxpException(-1, (String) lists.get(0));
                }
            } else {
                bindNote.setStatus(Integer.valueOf(-32));
                bindNote.setMsg("获取银行数据失败，请稍后再试");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.BANK_ERROR.exception();
            }

            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 银行卡提现
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/19 13:03
     */
    @PostMapping("Withdrawals")
    public ReturnObj Withdrawals(HttpServletRequest request, HttpServletResponse response) throws BxpException {

        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        java.util.Date date = new java.util.Date();
        String id = null;//奖励Id
        String BindType = null;
        try {
            id = request.getParameter("id");
            BindType = request.getParameter("BindType");
            if ((id == null || "".equals(id)) || (BindType == null || "".equals(BindType))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            //奖励开始
            ProjectAward pa = this.projAwardService.findById(Integer.valueOf(id));
            if (pa == null) {
                throw BxpExceptionEnum.NOT_AWARD.exception();
            }
            if (pa.getState().equals(0)) {
                throw BxpExceptionEnum.CANNOT_WITHDRAW2.exception();
            }
            if (pa.getFlag().equals(1) || pa.getFlag().equals(2)) {
                throw BxpExceptionEnum.CANNOT_WITHDRAW3.exception();
            }
            //奖励结束

            Integer userId = Integer.valueOf(pa.getUserid());//用户Id
            Float money = Float.valueOf(pa.getMoney().toString()); //金额
            String amount = String.valueOf(money);
            //用户开始
            Userinfo user = this.userinfoService.selectUserinfoByID(userId);
            if (user == null) {
                throw BxpExceptionEnum.WITHDRAW_DEFEATED.exception();
            }
            if (user.getIsdel().equals(1)) {
                throw BxpExceptionEnum.CANNOT_WITHDRAW.exception();
            }
            if ((userId.intValue()== 0) && (money.floatValue()==0.0F)) {
                throw BxpExceptionEnum.CANNOT_WITHDRAW1.exception();
            }

            String userLoginname = user.getLoginname();
            //用户结束

            BindNote bindNote = bankUtils.getBindNote("中国银行", date, 2, "0", 0, userLoginname);
            bindNote.setMoney(amount);

            // 银行卡开始
            BankCard bankCard = this.bankCardService.getBanknumByUserName(userLoginname);
            if (bankCard == null) {
                bindNote.setStatus(Integer.valueOf(-33));
                bindNote.setMsg("您未绑定银行卡,不能领取奖励");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.NOT_BANK.exception();
            }
            // 银行卡结束

            //提现时间开始
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(date);
            Integer a = Integer.valueOf(time.indexOf(":"));
            String st = time.substring(a.intValue() - 2, a.intValue());
            Integer hour = Integer.valueOf(st);
            if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.DATE_ERROR.exception();
            }
            //提现时间结束
            String banknum = bankCard.getBanknum();
            bindNote.setbNumber(banknum);
            Long l = Long.valueOf(date.getTime());
            String serialNumber = userLoginname + String.valueOf(l);
            Tradingnote t = new Tradingnote();
            t.setAccount(banknum);
            t.setAmont(amount);
            t.setPid(Integer.valueOf(id));
            t.setTradingTime(date);
            t.setType("0");
            t.setUsername(userLoginname);
            t.setUserid(userId);
            t.setHandingtime(date);
            t.setBankId(serialNumber);
            HttpClient httpClient = new DefaultHttpClient();
            String url = this.purseUrl + "/bonus/banks/bringForward?cellNo=" + userLoginname +
                    "&amount=" + amount + "&type=" + BindType + "&serialNumber=" +
                    serialNumber + "&pId=" + id;
            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                List<String> lists = bankUtils.getResponseValues(resp);
                bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                bindNote.setMsg((String) lists.get(0));
                t.setFlag(Integer.valueOf((String) lists.get(1)));
                t.setMsg((String) lists.get(0));
                if ((((String) lists.get(1)).equals("00")) || (((String) lists.get(1)).equals("10"))) {
                    ProjectAward projectAward = new ProjectAward();
                    projectAward.setId(Integer.valueOf(id));
                    projectAward.setFlag(2);
                    this.tradingnoteService.Withdrawals(t, bindNote, projectAward);
                    returnObj.setObj(null);
                    returnObj.setFlag("提现成功，以账户实际到账时间为准，请注意查收");
                } else if (((String) lists.get(1)).equals("06")) {
                    ProjectAward projectAward = new ProjectAward();
                    projectAward.setId(Integer.valueOf(id));
                    projectAward.setFlag(2);
                    this.tradingnoteService.Withdrawals(t, bindNote, projectAward);

                    throw BxpExceptionEnum.CANNOT_WITHDRAW4.exception();
                } else {
                    this.tradingnoteService.save(t);
                    this.bindNoteService.insertBindNote(bindNote);
                    throw new BxpException(-1, (String) lists.get(0));
                }
            } else {
                t.setFlag(Integer.valueOf(-32));
                t.setMsg("访问提现服务失败");
                bindNote.setStatus(Integer.valueOf(-32));
                bindNote.setMsg("获取银行数据失败，请稍后再试");
                this.tradingnoteService.save(t);
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.BANK_ERROR.exception();
            }
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 我的银行卡
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/19 13:40
     */
    @PostMapping("myBankCard")
    public ReturnObj myBankCard(HttpServletRequest request) throws BxpException {
        String username = null;
        try {
            username = request.getParameter("username");
            if (username == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            List<BankCard> bankCards = bankCardService.selectBankCardByUserName(username);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(bankCards);
            returnObj.setFlag("获取银行卡成功");
            return returnObj;
        } catch (BxpException E) {
            throw E;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping("abnormality")
    public String abnormality(@RequestBody String str, HttpServletRequest request, HttpServletResponse response) {
        String s = null;
        try {

            HashMap<String, Object> ma = new HashMap();

            Tradingnote t = new Tradingnote();

            JSONArray array = JSON.parseArray(str);

            for (int i = 0; i < array.size(); i++) {
                String string = array.getString(i);
                JSONObject js = JSON.parseObject(string);
                String bankId = js.getString("serialNumber");
                String cellNo = js.getString("cellNo");
                String state = js.getString("status");
                t.setBankId(bankId);
                t.setUsername(cellNo);
                Tradingnote tradingnote = this.tradingnoteService.findPidByUsernameAndBankId(t);
                if (tradingnote != null) {
                    Integer id = tradingnote.getPid();
                    ProjectAward projectAward = new ProjectAward();
                    projectAward.setId(Integer.valueOf(id));
                    projectAward.setFlag(Integer.valueOf(state));
                    projAwardService.editProjectAward(projectAward);
                    t.setExceptionalTime(new java.util.Date());
                    t.setStatus(Integer.valueOf(state));
                    if (state.equals("1")) {
                        t.setFlag(Integer.valueOf(-1));
                        t.setMsg("提现失败");
                    } else if (state.equals("3")) {
                        t.setFlag(Integer.valueOf(0));
                        t.setMsg("提现成功");
                    }
                    this.tradingnoteService.updateStatus(t);
                } else {
                    return new String("{'msg':'失败','success':false}".getBytes("iso-8859-1"), "utf-8");
                }
            }
        } catch (Exception ex) {

        }
        return s;

    }


    //微信功能

    /**
     * 我的微信
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("myWeiXin")
    public ReturnObj myWeiXin( HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String userID=null;
        ReturnObj returnObj = new ReturnObj();
        try {
            userID = request.getParameter("userID");

            if ((userID == null) || (userID.equals(""))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
        List<PWeChat> lists = this.wechatService.ListWechat(Integer.valueOf(userID));
            if(lists!=null && lists.size()>=1){
                PWeChat pWeChat=lists.get(0);
                if(pWeChat.getType()==1){
                    returnObj.setObj(pWeChat);
                    returnObj.setFlag("上次绑定失败，请重新绑定");
                }else if(pWeChat.getType()==2){
                    returnObj.setObj(pWeChat);
                    returnObj.setFlag("您的微信还未关注(中国银行贵州分行)公众号,请尽快关注并发送随机码,便于您领取奖励金.");
                }else{
                    returnObj.setObj(pWeChat);
                    returnObj.setFlag("微信已绑定成功");
                }
            }else{
                PWeChat pWeChat=new PWeChat();
                pWeChat.setType(0);
                returnObj.setObj(pWeChat);
                returnObj.setFlag("请无绑定微信");

            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
        return returnObj;
    }

    /**
     * 绑定微信
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("bindingWeChat")
    public ReturnObj bindingWeChat(HttpServletRequest request, HttpServletResponse response) throws BxpException  {
        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        BindNote bindNote = null;
        String userID = null;
        String CellNum =  null;
     try {
            userID = request.getParameter("userID");
            CellNum = request.getParameter("username");
            if ((userID == null) || (userID.equals("")) || (CellNum == null) || (CellNum.equals(""))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            java.util.Date date = new java.util.Date();
            Integer userid = Integer.valueOf(userID);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(date);
            Integer a = Integer.valueOf(time.indexOf(":"));
            String st = time.substring(a.intValue() - 2, a.intValue());
            Integer hour = Integer.valueOf(st);
            bindNote = bankUtils.getBindNote("微信", date, 0, "no", 1, CellNum);
            bindNote.setUserid(userid);
            PWeChat w = this.wechatService.selectWechat(userid);
            if(w==null){
                //还没有绑定微信
                if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                    throw BxpExceptionEnum.DATE_ERROR.exception();
                }
                HttpClient httpClient = new DefaultHttpClient();
                String url = this.purseUrl + "/bonus/banks/acceptAddUserBinding?BbsUserId=" + Long.valueOf(userID) + "&CellNum=" + CellNum + "&BindType=" + 1;
                HttpPost httpPost = new HttpPost(url);
                HttpResponse resp = httpClient.execute(httpPost);
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    List<String> lists = bankUtils.getResponseValues(resp);
                    if (((String) lists.get(1)).equals("00")) {
                        PWeChat wechat1 = new PWeChat();
                        wechat1.setUserid(userid.intValue());
                        wechat1.setUsername(CellNum);
                        wechat1.setCudate(date);
                        wechat1.setType(2);
                        wechat1.setCode((String) lists.get(2));
                        bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                        bindNote.setMsg("微信号绑定成功,请微信关注(中国银行贵州分行)公众号,并发送随机码,便于您领取奖励金.");
                        PWeChat pWeChat=this.wechatService.InsertWechat(wechat1,bindNote);
                        returnObj.setObj(pWeChat);
                        returnObj.setFlag("微信号绑定成功,请微信关注(中国银行贵州分行)公众号,并发送随机码,便于您领取奖励金.");
                    } else {
                        bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                        bindNote.setMsg((String) lists.get(0));
                        throw BxpExceptionEnum.REQUEST_FEILED.exception();
                    }
                }else{
                    bindNote.setStatus(Integer.valueOf(-32));
                    bindNote.setMsg("获取银行数据失败，请稍后再试");
                    this.bindNoteService.insertBindNote(bindNote);
                    throw BxpExceptionEnum.BANK_ERROR.exception();
                }
            }else if(w.getType()==3){
                bindNote.setStatus(Integer.valueOf(-48));
                bindNote.setMsg("您已有微信号绑定成功,不能再次绑定");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.BINDUNG_ERROR.exception();
            }else{
                bindNote.setStatus(Integer.valueOf(-35));
                bindNote.setMsg("您已绑定微信号,还未完成微信公众号关注功能，不能再次绑定,请去微信关注“中国银行贵州分行”公众号,以便领取奖励");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.WECHAT_ERROR.exception();
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
        return returnObj;
    }

    /**
     * 解绑微信
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("unBindingWeChat")
    public ReturnObj unBindingWeChat(HttpServletRequest request, HttpServletResponse response) throws  BxpException {
        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        BindNote bindNote = null;
        String userID =null;
        String CellNum =null;
        try {
            userID = request.getParameter("userID");
            CellNum = request.getParameter("username");
            if ((userID == null) || (userID.equals("")) || (CellNum == null) || (CellNum.equals(""))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            java.util.Date date = new java.util.Date();
            Integer userid = Integer.valueOf(userID);
            bindNote = bankUtils.getBindNote("微信", date, 1, "no", 1, CellNum);
            bindNote.setUserid(userid);
            PWeChat w = this.wechatService.selectWechat(userid);
            if (w != null) {
                Userinfo user = this.userinfoService.selectUserByLoginname(CellNum);
                if (user != null) {
                    HashMap<String, Object> maps = new HashMap();
                    maps.put("username", CellNum);
                    maps.put("type", "1");
                    Integer l = this.tradingnoteService.selectTradingnoteByUsernameAndType(maps);
                    if (l.intValue() >= 1) {
                        bindNote.setStatus(Integer.valueOf(-50));
                        bindNote.setMsg("您有奖励正在提现中,不能解绑");
                        this.bindNoteService.insertBindNote(bindNote);
                        throw BxpExceptionEnum.TIXIAN_ERROR.exception();
                    }else{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = df.format(date);
                        Integer a = Integer.valueOf(time.indexOf(":"));
                        String st = time.substring(a.intValue() - 2, a.intValue());
                        Integer hour = Integer.valueOf(st);
                        if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                            throw BxpExceptionEnum.DATE_ERROR.exception();
                        }else if(w.getType().equals(3)){
                            HttpClient httpClient = new DefaultHttpClient();
                            String url = this.purseUrl + "/bonus/banks/unbundling?BbsUserId=" +
                                    Long.valueOf(userID) + "&CellNum=" + CellNum + "&Type=" + 1;
                            HttpPost httpPost = new HttpPost(url);
                            HttpResponse resp = httpClient.execute(httpPost);
                            int statusCode = resp.getStatusLine().getStatusCode();
                            if (statusCode == 200) {
                                List<String> lists = bankUtils.getResponseValues(resp);
                                bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                                bindNote.setMsg((String) lists.get(0));
                                if (((String) lists.get(1)).equals("00")) {
                                    bindNote.setStatus(Integer.valueOf(0));
                                    bindNote.setMsg("微信解绑成功");
                                    this.wechatService.reomWechat(userid,bindNote);
                                    returnObj.setObj(null);
                                    returnObj.setFlag("解绑成功");
                                } else {
                                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                                }
                            } else {
                                bindNote.setStatus(Integer.valueOf(-32));
                                bindNote.setMsg("获取银行数据失败，请稍后再试");
                                this.bindNoteService.insertBindNote(bindNote);
                                throw BxpExceptionEnum.BANK_ERROR.exception();
                            }
                        }
                        else
                        {
                            bindNote.setStatus(Integer.valueOf(0));
                            bindNote.setMsg("解绑成功,失败1和未关注2");
                            this.wechatService.reomWechat(userid,bindNote);
                            returnObj.setObj(null);
                            returnObj.setFlag("解绑成功");
                        }
                    }
                }else{
                    bindNote.setStatus(Integer.valueOf(-51));
                    bindNote.setMsg("没有此用户，无需解绑");
                    this.bindNoteService.insertBindNote(bindNote);
                    throw BxpExceptionEnum.USER_ERROR.exception();
                }
            }else{
                bindNote.setStatus(Integer.valueOf(-51));
                bindNote.setMsg("没有此用户，无需解绑");
                this.bindNoteService.insertBindNote(bindNote);
                throw BxpExceptionEnum.USER_ERROR.exception();
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }

        return returnObj;
    }

    /**
     * 微信提现
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("weiXinWithdrawals")
    public ReturnObj weiXinWithdrawals(HttpServletRequest request, HttpServletResponse response, @RequestHeader(required = false, value = "Authorization") String Authorization)
            throws BxpException {
        ReturnObj returnObj = new ReturnObj();
        BankUtils bankUtils = new BankUtils();
        BindNote bindNote = null;
        String id =null;
        String type =null;
        try {
            id = request.getParameter("id");
            type=request.getParameter("BindType");
            if ((id == null) || (id.equals("")) || (type == null) || (type.equals(""))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            HashMap<String, Object> hm = new HashMap();
            hm.put("id", id);

            ProjectAward pa = this.projAwardService.findById(Integer.valueOf(id));

            if(pa!=null){
                Integer userID = Integer.valueOf(pa.getUserid());
                String format = new BigDecimal(String.valueOf(pa.getMoney())).toString();
                Float money = Float.valueOf(format);
                String amount = String.valueOf(money);
                Userinfo user = this.userinfoService.selectUserinfoByID(userID);
                if (user == null) {
                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                }else{
                    String cellNo = user.getLoginname();
                    if ((userID.intValue() != 0) && (money.floatValue() != 0.0F)) {
                        if (pa.getFlag().equals(0)) {
                            java.util.Date date = new java.util.Date();

                            bindNote = bankUtils.getBindNote("微信", date, 2, "no", 1, cellNo);
                            bindNote.setUserid(userID);
                            bindNote.setMoney(amount);

                            PWeChat w = this.wechatService.selectWechat(userID);
                            if (w != null) {
                                if (w.getType() == 3) {

                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String time = df.format(date);
                                    Integer a = Integer.valueOf(time.indexOf(":"));
                                    String st = time.substring(a.intValue() - 2, a.intValue());
                                    Integer hour = Integer.valueOf(st);
                                    if ((hour.intValue() < 1) || (hour.intValue() >= 23)) {
                                        throw BxpExceptionEnum.DATE_ERROR.exception();
                                    } else {
                                        Long l = Long.valueOf(date.getTime());
                                        String serialNumber = cellNo + String.valueOf(l);

                                        Tradingnote t = new Tradingnote();
                                        t.setUsername(cellNo);
                                        t.setUserid(userID);
                                        t.setTradingTime(date);
                                        t.setAccount(String.valueOf(w.getId()));
                                        t.setBankId(serialNumber);
                                        t.setAmont(amount);
                                        t.setType("1");
                                        t.setPid(Integer.valueOf(id));
                                        t.setHandingtime(date);

                                        HttpClient httpClient = new DefaultHttpClient();
                                        String url = this.purseUrl + "/bonus/banks/bringForward?bbsUserId=" +
                                                Long.valueOf(userID.intValue()) + "&cellNo=" + cellNo + "&amount=" +
                                                amount + "&type=" + type + "&serialNumber=" + serialNumber +
                                                "&pId=" + id;
                                        HttpPost httpPost = new HttpPost(url);
                                        HttpResponse resp = httpClient.execute(httpPost);

                                        int statusCode = resp.getStatusLine().getStatusCode();
                                        if (statusCode == 200) {
                                            List<String> lists = bankUtils.getResponseValues(resp);
                                            t.setFlag(Integer.valueOf((String) lists.get(1)));
                                            t.setMsg((String) lists.get(0));
                                            bindNote.setStatus(Integer.valueOf((String) lists.get(1)));
                                            if (((String) lists.get(1)).equals("00")) {

                                                hm.put("flag", Integer.valueOf(1));
                                                t.setFlag(Integer.valueOf(1));
                                                t.setMsg("微信领取奖励成功，以微信到账时间为准，请注意查收");
                                                bindNote.setMsg("微信领取奖励成功，以微信到账时间为准，请注意查收");

                                                this.projAwardService.updateProjectAward(hm,t,bindNote);

                                                returnObj.setObj(null);
                                                returnObj.setFlag("微信领取奖励成功，以微信到账时间为准，请注意查收");

                                            }
                                            if (((String) lists.get(1)).equals("10")) {

                                                hm.put("flag", Integer.valueOf(1));
                                                bindNote.setMsg((String) lists.get(0));
                                                t.setFlag(Integer.valueOf(1));
                                                t.setMsg("微信领取奖励成功，以微信到账时间为准，请注意查收");

                                                this.projAwardService.updateProjectAward(hm,t,bindNote);

                                                returnObj.setObj(null);
                                                returnObj.setFlag("微信领取奖励成功，以微信到账时间为准，请注意查收");

                                            } else if (((String) lists.get(1)).equals("06")) {
                                                hm.put("flag", Integer.valueOf(1));

                                                bindNote.setMsg("微信提现中,银行系统问题,若48小时内未到账,请联系贵阳百姓拍客服");

                                                t.setFlag(Integer.valueOf(1));
                                                t.setMsg("微信提现中,银行系统问题,若48小时内未到账,请联系贵阳百姓拍客服");

                                                this.projAwardService.updateProjectAward(hm,t,bindNote);

                                                throw BxpExceptionEnum.BANK_MISTAKE.exception();
                                            } else {

                                                bindNote.setMsg((String) lists.get(0));
                                                this.bindNoteService.insertBindNote(bindNote);

                                                throw BxpExceptionEnum.REQUEST_FEILED.exception();
                                            }
                                        } else {
                                            t.setFlag(Integer.valueOf(-32));
                                            t.setMsg("访问提现服务失败");
                                            bindNote.setStatus(Integer.valueOf(-32));
                                            bindNote.setMsg("获取银行数据失败，请稍后再试");
                                            this.tradingnoteService.save(t);//失败时
                                            this.bindNoteService.insertBindNote(bindNote);
                                            throw BxpExceptionEnum.BANK_ERROR.exception();
                                        }
                                    }

                                } else if (w.getType() == 2) {
                                bindNote.setStatus(Integer.valueOf(-42));
                                bindNote.setMsg("您的微信未关注(中国银行贵州分行)公众号，不能领取奖励");
                                this.bindNoteService.insertBindNote(bindNote);
                                throw BxpExceptionEnum.WECHAT_ERROR2.exception();
                            } else {
                                bindNote.setStatus(Integer.valueOf(-41));
                                bindNote.setMsg("您的微信绑定失败，不能领取奖励，请解绑后再绑定");
                                this.bindNoteService.insertBindNote(bindNote);
                                throw BxpExceptionEnum.WECHAT_ERROR3.exception();
                            }
                            }else{
                                bindNote.setStatus(Integer.valueOf(-39));
                                bindNote.setMsg("您未绑定微信，不能领取奖励");
                                this.bindNoteService.insertBindNote(bindNote);
                                throw BxpExceptionEnum.WECHAT_ERROR4.exception();
                            }
                        }else{
                            throw BxpExceptionEnum.BONUS_ALREADY.exception();
                        }
                    }else{
                        throw BxpExceptionEnum.BONUS_ERROR.exception();
                    }
                }
            }else{
                throw BxpExceptionEnum.BONUS_NULL.exception();
            }
        } catch (BxpException E) {
            throw E;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
        return returnObj;

    }

    /**
     * 提现状态检查
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("checkStatus")
    public ReturnObj checkStatus(@RequestBody String str, HttpServletRequest request, HttpServletResponse response) throws BxpException {
        ReturnObj returnObj=new ReturnObj();
        String username =null;
        String type =null;
        try{
            username = request.getParameter("username");
            type = request.getParameter("type");
            if ((username == null || "".equals(username)) || (type == null || "".equals(type))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Boolean user = this.userinfoService.isLoginnameExist(username);
            if (!user){
                throw BxpExceptionEnum.USER_NO_EXIST.exception();
            }
            HashMap<String, Object> maps = new HashMap();
            maps.put("username", username);
            maps.put("type", type);
            Integer l = this.tradingnoteService.selectTradingnoteByUsernameAndType(maps);
            if (l.intValue() >= 1) {
                throw BxpExceptionEnum.NOT_USER_AWARD.exception();
            } else {
                returnObj.setObj(null);
                returnObj.setFlag("可以解绑");
            }
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
        return returnObj;
    }

    @OriginalJson
    @PostMapping("/getWeiXinBindStatus")
    public String getWeiXinBindStatus(@RequestBody String str, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String s = null;
        try
        {
            JSONObject jsonObject = JSON.parseObject(str);


            String cellNo = jsonObject.getString("cellNo");
            String bindStatus = jsonObject.getString("bindStatus");

            PWeChat w = new PWeChat();
            w.setUsername(cellNo);
            w.setType(Integer.valueOf(bindStatus).intValue());
            this.wechatService.editPWeChat(w);
            s = new String("{'msg':'成功','success':true}".getBytes("iso-8859-1"), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            s = new String("{'msg':'失败','success':false}".getBytes("iso-8859-1"), "utf-8");
        }
        return s;
    }
    @OriginalJson
    @PostMapping("/WeiXinBringForwardStatus")
    public String WeiXinBringForwardStatus(@RequestBody String str, HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        String s = null;
        try
        {
            HashMap<String, Object> ma = new HashMap();

            Tradingnote t = new Tradingnote();
            JSONArray array = JSON.parseArray(str);

            for (int i = 0; i < array.size(); i++)
            {
                String string = array.getString(i);

                JSONObject js = JSON.parseObject(string);

                String bankId = js.getString("serialNumber");
                String cellNo = js.getString("cellNo");
                String state = js.getString("status");
                t.setBankId(bankId);
                t.setUsername(cellNo);

                Tradingnote tradingnote = this.tradingnoteService.findPidByUsernameAndBankId(t);
                if (tradingnote != null)
                {
                    Integer id = tradingnote.getPid();
                    ma.put("id", id);
                    ma.put("state", Integer.valueOf(state));
                    ProjectAward projectAward=new ProjectAward();
                    projectAward.setId(id);
                    projectAward.setFlag(Integer.valueOf(state));
                    this.projAwardService.editProjectAward(projectAward);

                    t.setHandingtime(new java.util.Date());
                    t.setState(Integer.valueOf(state));
                    if (state.equals("1"))
                    {
                        t.setFlag(Integer.valueOf(-1));
                        t.setMsg("提现失败");
                    }
                    else if (state.equals("3"))
                    {
                        t.setFlag(Integer.valueOf(0));
                        t.setMsg("提现成功");
                    }
                    this.tradingnoteService.updateState(t);
                }
                else
                {
                    return new String("{'msg':'失败','success':false}".getBytes("iso-8859-1"), "utf-8");
                }
            }
            s = new String("{'msg':'成功','success':true}".getBytes("iso-8859-1"), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            s = new String("{'msg':'失败','success':false}".getBytes("iso-8859-1"), "utf-8");
        }
        return s;
    }


    /**
     * 检查绑定 银行卡  或者微信
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 13:03
     */
    @PostMapping("isbinding")
    public ReturnObj isbinding(@RequestBody String str, HttpServletRequest request, HttpServletResponse response) throws BxpException  {

        ReturnObj returnObj=new ReturnObj();
        String username = null;
        String userId =  null;
        String type =  null;
        try {
            username = request.getParameter("username");
            userId = request.getParameter("userId");
            type = request.getParameter("type");//0：银行卡 1：微信
            if ((username == null || "".equals(username)) || (type == null || "".equals(type)) || (userId == null || "".equals(userId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (type.equals("0")){
                List<BankCard> BankCards = this.bankCardService.selectBankCardByUserName(username);
                if(BankCards==null || BankCards.size()==0){
                    ReturnMsg returnMsg=new ReturnMsg();
                    returnMsg.setObj("1");
                    returnObj.setObj(returnMsg);
                    returnObj.setFlag("您未绑定银行卡");
                }else{
                    ReturnMsg returnMsg=new ReturnMsg();
                    returnMsg.setObj("0");
                    returnObj.setObj(returnMsg);

                    returnObj.setFlag("您已绑定银行卡");
                }
            }else{
                List<PWeChat>  pWeChats=wechatService.ListWechat(Integer.valueOf(userId).intValue());

                if(pWeChats==null || pWeChats.size()==0){
                    ReturnMsg returnMsg=new ReturnMsg();
                    returnMsg.setObj("1");
                    returnObj.setObj(returnMsg);
                    returnObj.setFlag("您未绑定微信");
                }else{
                    ReturnMsg returnMsg=new ReturnMsg();
                    returnMsg.setObj("0");
                    returnObj.setObj(returnMsg);
                    returnObj.setFlag("您已绑定微信");
                }
            }
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

}
