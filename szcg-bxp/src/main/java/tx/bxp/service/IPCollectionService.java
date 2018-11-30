package tx.bxp.service;

import org.springframework.web.bind.annotation.RequestHeader;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.entity.PCollection;
import tx.bxp.entity.PWeChat;
import tx.bxp.entity.Push;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:收藏列表管理
 * @author:pck
 * @create: 2018-06-07 16:52
 **/
public interface IPCollectionService {

    /**
     * 保存收藏信息
     * @author chenmc
     * @date 2018/7/2 14:10
     * @param
     * @return
     */
    public Integer savepcollection(PCollection pcollection);

    /**
     * 删除收藏纪录
     * @author chenmc
     * @date 2018/7/2 14:25
     * @param
     * @return
     */
    public Integer remoevepcollection(HashMap<String,Object> map);

    /**
     * 查询收藏列表
     * @author chenmc
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
    public ArrayList<BxpProjectList> ListPCollection(HashMap<String,Integer> map);


}
