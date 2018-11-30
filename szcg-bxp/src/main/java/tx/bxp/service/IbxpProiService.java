package tx.bxp.service;

import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.MyBxpProject;
import tx.bxp.entity.Userinfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02
 **/

public interface IbxpProiService {

    /**
     * 新增案件
     * @author chenmc
     * @date 2018/9/11 16:18
     * @param
     * @return
     */
    public void savebxpPro(String address,String lon,String lat,String mome,String userId,MultipartFile[] files,String seq);

    /**
     * 首页案件列表
     * @author chenmc
     * @date 2018/9/11 16:18
     * @param
     * @return
     */
    public ArrayList<BxpProjectList> BxpProjectList();


    /**
     * 案件详情
     * @author chenmc
     * @date 2018/9/11 16:18
     * @param
     * @return
     */
    public BxpProject getBxpProject(Integer id,Integer type,Integer uesrId);


    /**
     * 我的案件列表
     * @author chenmc
     * @date 2018/9/11 16:18
     * @param
     * @return
     */
    public ArrayList<MyBxpProject> MyBxpProjectList(HashMap<String,Object> map);

    public ArrayList<HashMap<String,Object>> getsum();

    public Integer getuserIdCount(Integer userId);

    public Userinfo getBxpProjectByID(Integer id);



}
