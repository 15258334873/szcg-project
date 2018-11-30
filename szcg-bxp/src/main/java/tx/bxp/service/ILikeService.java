package tx.bxp.service;

/**
 * 点赞功能
 * @author chenmc
 * @date 2018/8/3 14:27
 * @param
 * @return
 */
public interface ILikeService {

    /**
     * 用户点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    public void save(Integer userid,Integer pId,Integer type);

    /**
     * 取消点赞
     * @author chenmc
     * @date 2018/8/17 10:39
     * @param
     * @return
     */
    public void remove(Integer userid,Integer pId,Integer type);

    public Integer getlike(Integer userid,Integer pId,Integer type);




}
