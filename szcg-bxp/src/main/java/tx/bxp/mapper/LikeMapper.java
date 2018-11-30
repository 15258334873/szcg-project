package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.Like;
/**
 * 点赞功能
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface LikeMapper {

    /**
     * 用户点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    void InsertLike(Like like);

    /**
     * 取消点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    void DeleteLike(Like like);


    public Integer selectLike( Like like);

}
