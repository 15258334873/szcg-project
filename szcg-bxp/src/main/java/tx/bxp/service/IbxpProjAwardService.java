package tx.bxp.service;

import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.MyProjectAward;
import tx.bxp.entity.BindNote;
import tx.bxp.entity.ProjectAward;
import tx.bxp.entity.Tradingnote;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02
 **/

public interface IbxpProjAwardService {

    /**
     * 个人奖励总和
     * @author chenmc
     * @date 2018/9/11 16:18
     * @param
     * @return
     */
    public tx.bxp.dto.ProjectAward getaccount(Integer id);

    /**
     * 根据主键ID查询记录
     * @author chenmc
     * @date 2018/9/18 16:51
     * @param
     * @return
     */
    public ProjectAward findById(Integer id);


    public void editProjectAward (ProjectAward projectAward);


    public void updateProjectAward (HashMap<String,Object> map, Tradingnote t , BindNote bindNote);


    public ArrayList<MyProjectAward> MyProjectAwardList(HashMap<String,Object> map);



}
