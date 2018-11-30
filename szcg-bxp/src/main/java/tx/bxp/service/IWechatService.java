package tx.bxp.service;

import tx.bxp.entity.BindNote;
import tx.bxp.entity.PWeChat;

import java.util.List;

/**
 * @program: tx.framework
 * @description:我的微信管理
 * @author:pck
 * @create: 2018-06-07 16:52
 **/
public interface IWechatService {

    /**
     * 绑定微信号
     * @Title: insertWechat
     * @Description: 绑定微信号
     * @param @param wechat
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public Integer saveWechat(PWeChat wechat);

    /**
     * 解绑我的微信
     * @Title: reomWechat
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param wechat
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public void reomWechat(Integer userId,BindNote bindNote);

    /**
     * 修改微信绑定状态
     * @author chenmc
     * @date 2018/7/4 10:32
     * @param
     * @return
     */
    public Integer editPWeChat(PWeChat wechat);

    /**
     * 获取微信列表
     * @author chenmc
     * @date 2018/7/4 10:33
     * @param
     * @return
     */
    public List<PWeChat> ListWechat(int userId);

    /**
     * 获取微信
     * @author chenmc  
     * @date 2018/7/4 10:36
     * @param   
     * @return   
     */ 
    public PWeChat GetWechat(int Id);

    PWeChat selectWechat(Integer userid);


    public PWeChat InsertWechat(PWeChat wechat,BindNote bindNote);
}
