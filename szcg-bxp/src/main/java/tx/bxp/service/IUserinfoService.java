package tx.bxp.service;


import org.springframework.web.multipart.MultipartFile;
import tx.bxp.entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tx.bxp.entity.Userinfo;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-06-28 16:44
 **/
public interface IUserinfoService   {

        /**
         * 根据nickname查用户
         * @Title: selectUserinfoBynickname
         * @Description: 查询用户
         * @param @param
         * @param @return    设定文件
         * @return    返回类型
         * @throws
         */
        public  Userinfo selectUserinfoBynickname(String nickname);

        /**
         * 根据id查用户
         * @Title: selectUserinfoByID
         * @Description: 查询用户
         * @param @param
         * @param @return    设定文件
         * @return    返回类型
         * @throws
         */
        public  Userinfo selectUserinfoByID(int id);

        /**
         * 插入用户信息
         * @author FYZ
         * @date 2018/7/2 14:29
         * @param
         * @return
         */
        public void insertUserInfo(Userinfo userinfo);

        /**
         * 更新用户信息
         * @author FYZ
         * @date 2018/7/2 14:29
         * @param
         * @return
         */
        public Userinfo updateUser(Userinfo userinfo);

        public void updateUser1(Userinfo userinfo);


        /**
         * 修改用户登录密码
         * @author FYZ
         * @date 2018/7/4 14:29
         * @param
         * @return
         */
        public void updatePassword(Integer id,String password);

        /**
         * 修改用户支付密码
         * @author FYZ
         * @date 2018/7/4 14:29
         * @param
         * @return
         */
        public void updatePaypassword(Integer id,String paypassword);

        /**
         * 用户修改头像
         * @author FYZ
         * @date 2018/7/6 14:29
         * @param
         * @return
         */
        public Userinfo photoUp(MultipartFile file,Integer id)  throws Exception;

        /**
         * 重置用户支付密码
         * @author FYZ
         * @date 2018/7/6 14:29
         * @param
         * @return
         */
        public void resetPaypassword(Integer id,String paypassword);

        /**
         * 重置用户登录密码
         * @author FYZ
         * @date 2018/7/6 14:29
         * @param
         * @return
         */
        public void resetPassword(Integer id,String password);

        public boolean  isLoginnameExist(String Loginname);

        public boolean  isIdExist(Integer Id);

        public String  getpassword(Integer Id);

        public String  getpaypassword(Integer Id);

        public Integer  getID(String loginname);

        /**
         * @author 用户登录
         * @date 2018/9/6 9:38
         * @param
         * @return
         */
        public Userinfo login(String phone,String password,String flag,String version,String channelid);

        public Integer getunionid(String unionid);

        public boolean  selectUserinfoByIDandPay(Integer id,String paypassword);

        public Integer deleteUser(Integer id);


        Userinfo selectUserByLoginname(String cellNum);
}





