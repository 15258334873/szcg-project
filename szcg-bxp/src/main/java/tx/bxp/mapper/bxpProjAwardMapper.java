package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.dto.MyProjectAward;
import tx.bxp.entity.ProjectAward;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02 10:00
 **/
@Mapper
public interface bxpProjAwardMapper {
    /**
     * 个人奖励总和
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/11 16:18
     */
    public ProjectAward getaccount(Integer id);

    /**
     * 查询已领取金额
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/14 16:27
     */
    public ProjectAward Cumulative(Integer id);

    /**
     * 根据主键ID查询记录
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/18 16:51
     */
    public ProjectAward findById(Integer id);


    /**
     * 更新奖励信息
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 10:28
     */
    public void updateProjectAward(ProjectAward projectAward);


    /**
     * 更新奖励信息
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/20 10:28
     */
    public void editProjectAward(HashMap<String,Object> map);


    public ArrayList<MyProjectAward> selectProjectAwardByUserId(HashMap<String,Object> map);

}
