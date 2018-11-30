package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.BankCard;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02 10:00
 **/

@Mapper
public interface BankCardMapper {

    /**
     * 查询用户条银行卡信息（用户Id）
     *
     * @param @param  nickname
     * @param @return 设定文件
     * @return 返回类型
     * @throws
     * @Title: selectBankCardByUsername
     * @Description: 用户信息表
     */
    List<BankCard> selectBankCardByUserid(HashMap<String, Object> map);


    /**
     * 查询用户条银行卡信息（用户名）
     *
     * @param @param  nickname
     * @param @return 设定文件
     * @return 返回类型
     * @throws
     * @Title: selectBankCardByUsername
     * @Description: 用户信息表
     */
    List<BankCard> selectBankCardByUserName(String username);


    /**
     * 查询银行卡信息（银行卡id）
     *
     * @param @param  nickname
     * @param @return 设定文件
     * @return 返回类型
     * @throws
     * @Title: selectBankCardByUsername
     * @Description: 用户信息表
     */
    BankCard selectBankCardByid(int id);

    /**
     * 添加到银行卡信息
     *
     * @param @param
     * @param @return 设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: insertBankCard
     * @Description: 添加到银行卡信息表
     */
    void insertBankCard(BankCard bankcard);

    /**
     * 更新银行卡信息
     *
     * @param @param
     * @param @return 设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: updateBankCard
     * @Description: 更新到银行卡信息表
     */
    void updateBankCard(BankCard bankcard);

    /**
     * 根据Id删除银行卡信息
     *
     * @param @param
     * @param @return 设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: deleteBankCard
     * @Description: 从到银行卡信息表中删除
     */
    void deleteBankCard(int id);


    Integer selectBanknum(String banknum);

    /**
     * 根据银行卡号删除银行卡信息
     * @author chenmc
     * @date 2018/9/20 10:31
     * @param
     * @return
     */
    public Integer removeBankCard(String CellNum);


    public BankCard selectBanknumByUserName(String userLoginname);
}
