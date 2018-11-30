package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.dto.MyProjectAward;
import tx.bxp.entity.BindNote;
import tx.bxp.entity.ProjectAward;
import tx.bxp.entity.Tradingnote;
import tx.bxp.mapper.BankCardMapper;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.mapper.TradingnoteMapper;
import tx.bxp.mapper.bxpProjAwardMapper;
import tx.bxp.service.IbxpProjAwardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @program: szcg-project
 * @description: 个人奖励
 * @author:pck
 * @create: 2018-09-14 14:44
 **/
@Service
@Transactional
public class bxpProjAwardServiceImpl implements IbxpProjAwardService {

    @Autowired
    private bxpProjAwardMapper bxpProjAward;

    @Autowired
    private BindNoteMapper bindNoteMapper;

    @Autowired
    private TradingnoteMapper tradingnoteMapper;

    @Override
    public tx.bxp.dto.ProjectAward getaccount(Integer id) {
        ProjectAward getaccount = bxpProjAward.getaccount(id);
        ProjectAward getaccount1 = bxpProjAward.Cumulative(id);
        tx.bxp.dto.ProjectAward projectAward = new tx.bxp.dto.ProjectAward();
        projectAward.setIntegral(getaccount.getScore() + getaccount1.getScore());
        projectAward.setNoreceive(getaccount.getMoney());
        projectAward.setCumulative(getaccount1.getMoney());
        return projectAward;
    }

    @Override
    public ProjectAward findById(Integer id) {
        return bxpProjAward.findById(id);
    }

    @Override
    public void editProjectAward(ProjectAward projectAward) {
        bxpProjAward.updateProjectAward(projectAward);
    }

    @Override
    public void updateProjectAward(HashMap<String,Object> map, Tradingnote t , BindNote bindNote) {
        bindNoteMapper.insertBindNote(bindNote);
        tradingnoteMapper.save(t);
        bxpProjAward.editProjectAward(map);
    }

    @Override
    public ArrayList<MyProjectAward> MyProjectAwardList(HashMap<String, Object> map) {
        return bxpProjAward.selectProjectAwardByUserId(map);
    }
}
