package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.mapper.PushMapper;
import tx.bxp.entity.Push;
import tx.bxp.service.IPushService;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 10:28
 **/
@Transactional
@Service
public class PushServiceImpl implements IPushService {

    @Autowired
    private PushMapper pushMapper;

    //根据用户id查询
    @Override
    public Push getUserid(Integer userId){
        return pushMapper.findByUserid(userId);
    }

    //保存
    @Override
    public void save(Push push) {
        pushMapper.save(push);
    }

    //更新
    @Override
    public void UpdatePush(Push push){
        pushMapper.update(push);
    }

    //删除
    @Override
    public void DeletePush(Integer userId){
        pushMapper.delete(userId);
    }




}
