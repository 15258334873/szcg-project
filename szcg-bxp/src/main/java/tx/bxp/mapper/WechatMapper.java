package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tx.bxp.entity.PWeChat;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface WechatMapper {

    /**
     * 绑定微信号
     * @Title: insertWechat
     * @Description: 绑定微信号
     * @param @param wechat
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    Integer insertWechat(PWeChat wechat);

    /**
     * 解绑我的微信
     * @Title: reomWechat
     * @Description:  解绑我的微信
     * @param @param wechat
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    Integer deleteWechat(Integer Id);

    /**
     * 查询我的微信记录
     * @author chenmc
     * @date 2018/7/3 14:51
     * @param
     * @return
     */
    List<PWeChat> selectWechatByUserId(int userId);

    /**
     * 修改微信状态
     * @author chenmc
     * @date 2018/7/3 14:52
     * @param
     * @return
     */
    Integer updateWechat(PWeChat wechat);

    /**
     * 获取微信
     * @author chenmc  
     * @date 2018/7/4 10:36
     * @param   
     * @return   
     */ 
    PWeChat selectWechatById(int Id);

    PWeChat selectWechat(Integer userid);
}
