package tx.bxp.service;

import java.util.Collection;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:38
 **/
public interface ICollectionService {

    /**
     * 添加到我的收藏
     * @Title: insertCollection
     * @Description: 添加到我的收藏
     * @param @param smsvalidate
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public Integer insertCollection(Collection collection);

    /**
     * 取消我的收藏
     * @Title: deleteCollection
     * @Description: 取消我的收藏
     * @param @param id
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public Integer deleteCollection(Integer id);

    /**
     * 我的收藏列表
     * @Title: selectCollectionByUserId
     * @Description: 我的收藏列表
     * @param @param userId
     * @param @return    设定文件
     * @return List<Collection>    返回类型
     * @throws
     */
    public List<Collection> selectCollectionByUserId(Integer userId);






}
