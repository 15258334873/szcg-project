package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BindNote;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.mapper.WechatMapper;
import tx.bxp.entity.PWeChat;
import tx.bxp.service.IWechatService;

import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 10:46
 **/
@Transactional
@Service
public class WechatServiceImpl implements IWechatService {

   @Autowired
    private WechatMapper wechatMapper;

    @Autowired
    private BindNoteMapper bindNoteMapper;


    @Override
    public Integer saveWechat(PWeChat wechat) {
        Integer i =  wechatMapper.insertWechat(wechat);

        return i;
    }

    @Override
    public void reomWechat(Integer Id,BindNote bindNote) {
        bindNoteMapper.insertBindNote(bindNote);
         wechatMapper.deleteWechat(Id);
       ;
    }

    @Override
    public Integer editPWeChat(PWeChat wechat) {
        Integer i= wechatMapper.updateWechat(wechat);
        return i;

    }

    @Override
    public List<PWeChat> ListWechat(int userId) {

        List<PWeChat> PWeChats=wechatMapper.selectWechatByUserId(userId);
        return PWeChats;
    }

    @Override
    public PWeChat GetWechat(int Id) {
        PWeChat pWeChat=wechatMapper.selectWechatById(Id);
        return pWeChat;

    }

    @Override
    public PWeChat selectWechat(Integer userid) {
        System.out.println(userid);
        PWeChat pWeChat=wechatMapper.selectWechat(userid);
        return wechatMapper.selectWechat(userid);
    }


    @Override
    public PWeChat InsertWechat(PWeChat wechat, BindNote bindNote) {
        bindNoteMapper.insertBindNote(bindNote);
        wechatMapper.insertWechat(wechat);
        int id=wechat.getId();
        System.out.println(id);
        return wechat;
    }
}
