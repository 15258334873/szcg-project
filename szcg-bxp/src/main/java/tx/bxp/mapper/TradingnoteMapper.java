package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tx.bxp.entity.Tradingnote;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface TradingnoteMapper {

    //保存交易记录
    void save(Tradingnote tradingnote);

    //查询提现中的条数
    Integer selectTradingnoteByUsernameAndType(HashMap<String, Object> maps);

    //对账查询
    List<Tradingnote> selectTradingnotes(HashMap<String, Object> m);

    //查询明细
    List<HashMap<String, Object>> selectTradingnote(HashMap<String, Object> m);

    //根据username和bankId查询用户交易记录
    Tradingnote findPidByUsernameAndBankId(Tradingnote tradingnote);

    //更改交易状态和提示信息
    void updateTradingnote(Tradingnote tradingnote);

    //微信回调更改交易方法
    void updateState(Tradingnote tradingnote);

    //06回调更改交易方法
    void updateStatus(Tradingnote tradingnote);

    //对账更改交易方法
    void updateCondition(Tradingnote tradingnote);

    //根据pId更改交易记录
    void updateTradingnote2(Tradingnote tradingnote);


}
