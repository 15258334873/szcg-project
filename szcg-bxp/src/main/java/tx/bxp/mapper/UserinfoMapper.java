package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.entity.ProjectFile;
import tx.bxp.entity.Userinfo;
import tx.bxp.entity.Usermanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-06-28 10:27
 **/
@Mapper
public interface UserinfoMapper {

    /**
     * 添加到一条用户信息
     * @Title: insertCollection
     * @Description: 添加到我的收藏
     * @param @param
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    void insertUserInfo(Userinfo userinfo);

    /**
     * 更新用户信息表
     * @Title: updateUser
     * @Description: 用户列表
     * @param @param
     * @param @return
     * @return  返回类型
     * @throws
     */
    void updateUser(Userinfo userinfo);


    public String SelectUserInfoByLoginName(String LoginName);

    public Integer SelectUserInfoById(Integer id);

    public String SelectpasswordById(Integer id);


    public String SelectpaypasswordById(Integer id);

    public  Integer  SelectidByloginname(String loginname);

    /**
     * @author 用户登录
     * @date 2018/9/6 9:38
     * @param
     * @return
     */
    public Userinfo login(Userinfo userinfo);

    public Userinfo SelectUserById(Integer id);


    public Integer SelectUserByUnionid(String unionid);

    public Userinfo  selectUserinfoByIDandPay(ConcurrentHashMap map);

    public Integer deleteUser(Integer id);


    Userinfo selectUserByLoginname(String loginname);
}


