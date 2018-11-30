package tx.bxp.service;

import tx.bxp.entity.Push;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:48
 **/
public interface IPushService {

    /**
     * @author chenmc
     * @date 2018/6/8 10:29
     * @param
     * @return
     */
    public Push getUserid(Integer userId);

    /**
     * @author chenmc
     * @date 2018/6/8 10:30
     * @param
     * @return
     */
    public void save(Push push);

    /**
     * @author chenmc
     * @date 2018/6/8 10:30
     * @param
     * @return
     */
    public void UpdatePush(Push push);

    /**
     * @author chenmc
     * @date 2018/6/8 10:30
     * @param
     * @return
     */
    public void DeletePush(Integer userId);


}
