package tx.bxp.mapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.entity.PCollection;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:收藏功能
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface PCollectionMapper {

    /**
     * 添加收藏列表
     * @author chenmc
     * @date 2018/7/2 14:50
     * @param
     * @return
     */
    public Integer InsertPcollection(PCollection pcollection);

    /**
     * 删除收藏纪录
     * @author chenmc
     * @date 2018/7/3 9:58
     * @param
     * @return
     */
    public Integer DeletePcollection( HashMap<String,Object> map);
    /**
     * 查询个人收藏记录
     * @author chenmc
     * @date 2018/7/3 10:03
     * @param
     * @return
     */
    public ArrayList<BxpProjectList> selectPCollectionByUserId(HashMap<String,Integer> map);






}
