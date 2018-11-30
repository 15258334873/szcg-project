package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.BrowseLike;

/**
 * @program: szcg-project
 * @description: 浏览点赞
 * @author:pck
 * @create: 2018-08-02 09:44
 **/
@Mapper
public interface BrowseLikeMapper {

    public Integer InsertBrowseLike (BrowseLike browseLike);

    public Integer updateBrowseLike (BrowseLike browseLike);

    public Integer selectBrowseLike (BrowseLike browseLike);

}
