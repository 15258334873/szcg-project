package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BindNote;
import tx.bxp.entity.ProjectAward;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.mapper.TradingnoteMapper;
import tx.bxp.entity.Tradingnote;
import tx.bxp.mapper.bxpProjAwardMapper;
import tx.bxp.service.ITradingnoteService;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 10:41
 **/
@Transactional
@Service
public class TradingnoteServiceImpl implements ITradingnoteService {

    @Autowired
    private TradingnoteMapper tradingnoteMapper;

    @Autowired
    private BindNoteMapper bindNoteMapper;

    @Autowired
    private bxpProjAwardMapper bxpProjAward;

    //保存交易记录
    @Override
    public void save(Tradingnote tradingnote) {
        tradingnoteMapper.save(tradingnote);
    }

    //查询提现中的条数
    @Override
    public Integer selectTradingnoteByUsernameAndType(HashMap<String, Object> maps) {
        return tradingnoteMapper.selectTradingnoteByUsernameAndType(maps);
    }


    //对账查询
    public List<Tradingnote> selectTradingnotes(HashMap<String, Object> m){
        return tradingnoteMapper.selectTradingnotes(m);
    }

    //查询明细
    @Override
    public List<HashMap<String, Object>> selectTradingnote(HashMap<String, Object> m) {
        // TODO Auto-generated method stub
        return tradingnoteMapper.selectTradingnote(m);
    }

    //根据username和bankId查询用户交易记录
    public Tradingnote findPidByUsernameAndBankId(Tradingnote tradingnote){
        return tradingnoteMapper.findPidByUsernameAndBankId(tradingnote);
    }

    //更改交易记录
    public void updateTradingnote(Tradingnote tradingnote){
        tradingnoteMapper.updateTradingnote(tradingnote);
    }

    //微信回调更改交易方法
    public void updateState(Tradingnote tradingnote){
        tradingnoteMapper.updateState(tradingnote);
    }

    //06回调更改交易方法
    public void updateStatus(Tradingnote tradingnote){
        tradingnoteMapper.updateStatus(tradingnote);
    }

    //对账更改交易方法
    public void updateCondition(Tradingnote tradingnote){
        tradingnoteMapper.updateCondition(tradingnote);
    }

    //根据pId更改交易记录
    public void updateTradingnote2(Tradingnote tradingnote){
        tradingnoteMapper.updateTradingnote2(tradingnote);
    }

    @Override
    public void Withdrawals(Tradingnote tradingnote, BindNote bindNote, ProjectAward projectAward) {
        tradingnoteMapper.save(tradingnote);

        bindNoteMapper.insertBindNote(bindNote);

        bxpProjAward.updateProjectAward(projectAward);

    }
}