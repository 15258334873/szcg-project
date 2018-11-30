package tx.bxp.service;

import tx.bxp.entity.PseeLike;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:51
 **/
public interface IPseeLikeService {

    Integer savePseeLike(PseeLike pseeLike);

    Integer editPseeLikeSee(PseeLike pseeLike);

    Integer editPseeLikeLike(PseeLike pseeLike);
}
