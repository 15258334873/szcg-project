package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.PseeLike;
import tx.bxp.mapper.PseeLikeMapper;
import tx.bxp.service.IPseeLikeService;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-17 10:31
 **/

@Transactional
@Service
public class PseeLikeServiceImpl  implements IPseeLikeService {

    @Autowired
    private PseeLikeMapper pseeLikeMapper;

    @Override
    public Integer savePseeLike(PseeLike pseeLike) {

        return  pseeLikeMapper.InsertPseeLike(pseeLike);
    }

    @Override
    public Integer editPseeLikeSee(PseeLike pseeLike) {

        return   pseeLikeMapper.UpdatePseeLike(pseeLike);
    }

    @Override
    public Integer editPseeLikeLike(PseeLike pseeLike) {
        return   pseeLikeMapper.UpdatePseeLike(pseeLike);
    }
}
