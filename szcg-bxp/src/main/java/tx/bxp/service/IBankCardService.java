package tx.bxp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BankCard;
import tx.bxp.entity.BindNote;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02
 **/

public interface IBankCardService {

    /**
     * 查询我绑定的银行卡列表
     * @Title: selectBlankcardByUserId
     * @Description: 查询我绑定的银行卡列表
     * @param @param UserId
     * @param @return    设定文件
     * @return List<Blankcard>    返回类型
     * @throws
     */
    public  List<BankCard> selectBankCardByUserid(HashMap<String, Object> map);

    /**
     * 查询我绑定的银行卡列表
     * @Title: selectBlankcardByUserId
     * @Description: 查询我绑定的银行卡列表
     * @param @param UserId
     * @param @return    设定文件
     * @return List<Blankcard>    返回类型
     * @throws
     */
    public  List<BankCard> selectBankCardByUserName(String UserName);

    /**
     * 查询我绑定的银行卡详情
     * @Title: selectBlankcardByUserId
     * @Description: 查询我绑定的银行卡列表
     * @param @param UserId
     * @param @return    设定文件
     * @return List<Blankcard>    返回类型
     * @throws
     */
    public  BankCard selectBankCardByid(int id);

    /**
     * 插入一条银行卡信息
     * @author FYZ
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
    public void insertBankCard(BankCard bankcard);

    /**
     * 更新一条银行卡信息
     * @author FYZ
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
    public void updateBankCard(BankCard bankcard);

    /**
     * 删除一条银行卡信息
     * @author FYZ
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
    public void deleteBankCard(int id);


    public Integer selectBanknum(String banknum);


    public Integer removeBankCard(String CellNum);


    public BankCard  getBanknumByUserName(String userLoginname);

    /**
     * 绑定银行卡并插入日志
     * @author chenmc
     * @date 2018/9/20 9:44
     * @param
     * @return
     */
    public void binding( BindNote bindNote, BankCard bankCard);


    /**
     * 解绑银行卡并插入日志
     * @author chenmc
     * @date 2018/9/20 9:44
     * @param
     * @return
     */
    public void unBinding( BindNote bindNote,String CellNum);



}
