package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.entity.PCollection;
import tx.bxp.mapper.PCollectionMapper;
import tx.bxp.service.IPCollectionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:收藏列表管理
 * @author:pck
 * @create: 2018-06-07 16:52
 **/
@Transactional
@Service
public class PCollectionServiceImpl implements IPCollectionService {

    @Autowired
    private PCollectionMapper pcollectionmapper;


    /**
     * 保存收藏信息
     * @author chenmc
     * @date 2018/7/2 14:10
     * @param
     * @return
     */
    @Override
    public Integer savepcollection(PCollection pcollection) {
        Integer i= pcollectionmapper.InsertPcollection(pcollection);
        return i;
    }

    /**
     * 删除收藏纪录
     * @author chenmc
     * @date 2018/7/2 14:25
     * @param
     * @return
     */
    @Override
    public Integer remoevepcollection( HashMap<String,Object> map) {
        Integer i= pcollectionmapper.DeletePcollection(map);
        return i;
    }

    /**
     * 查询收藏列表
     * @author chenmc
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
    @Override
    public ArrayList<BxpProjectList> ListPCollection(HashMap<String,Integer> map) {
        return  pcollectionmapper.selectPCollectionByUserId(map);
    }

}
