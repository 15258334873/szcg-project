package tx.bxp.service.Impl;

import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BrowseLike;
import tx.bxp.mapper.BrowseLikeMapper;
import tx.bxp.mapper.LikeMapper;
import tx.bxp.service.ILikeService;
import tx.bxp.entity.Like;

/**
 * 点赞功能
 * @author chenmc
 * @date 2018/8/3 14:27
 * @param
 * @return
 */

@Transactional
@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private BrowseLikeMapper browseLikeMapper;

    /**
     * 用户点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    @Override
    public void save(Integer userid,Integer pId,Integer type) {

        BrowseLike browseLike =new BrowseLike();
        browseLike.setType(type);
        browseLike.setLikenumber(1);
        browseLike.setPid(pId);
        browseLikeMapper.updateBrowseLike(browseLike);

        Like like=new Like();
        like.setPid(pId);
        like.setType(type);
        like.setUserid(userid);
        likeMapper.InsertLike(like);
    }

    /**
     * 取消点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    @Override
    public void remove(Integer userid,Integer pId,Integer type) {
        BrowseLike browseLike =new BrowseLike();
        browseLike.setType(type);
        browseLike.setLikenumber(-1);
        browseLike.setPid(pId);
        browseLikeMapper.updateBrowseLike(browseLike);

        Like like=new Like();
        like.setPid(pId);
        like.setType(type);
        like.setUserid(userid);
        likeMapper.DeleteLike(like);
    }

    @Override
    public Integer getlike(Integer userid, Integer pId, Integer type) {
        Like like=new Like();
        like.setPid(pId);
        like.setType(type);
        like.setUserid(userid);
      return   likeMapper.selectLike(like);
    }

}
