package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.PseeLike;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-17 10:17
 **/


@Mapper
public interface PseeLikeMapper {

    Integer InsertPseeLike(PseeLike pseeLike);

    Integer UpdatePseeLike(PseeLike pseeLike);


}
