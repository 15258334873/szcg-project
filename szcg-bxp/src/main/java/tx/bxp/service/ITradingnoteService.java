package tx.bxp.service;

import tx.bxp.entity.BindNote;
import tx.bxp.entity.ProjectAward;
import tx.bxp.entity.Tradingnote;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:50
 **/
public interface ITradingnoteService {

    //保存交易记录
    public void save(Tradingnote tradingnote);

    //查询提现中的条数
    public Integer selectTradingnoteByUsernameAndType(HashMap<String, Object> maps);

    //对账查询
    public List<Tradingnote> selectTradingnotes(HashMap<String, Object> m);

    //查询明细
    public List<HashMap<String, Object>> selectTradingnote(HashMap<String, Object> m);

    //根据username和bankId查询用户交易记录
    public Tradingnote findPidByUsernameAndBankId(Tradingnote tradingnote);

    //更改交易记录
    public void updateTradingnote(Tradingnote tradingnote);

    //微信回调更改交易方法
    public void updateState(Tradingnote tradingnote);

    //06回调更改交易方法
    public void updateStatus(Tradingnote tradingnote);

    //对账更改交易方法
    public void updateCondition(Tradingnote tradingnote);

    //根据pId更改交易记录
    public void updateTradingnote2(Tradingnote tradingnote);

    public void Withdrawals(Tradingnote tradingnote, BindNote bindNote , ProjectAward projectAward );



}
