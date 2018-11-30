package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.entity.BindNote;
import tx.bxp.service.IBindNoteService;

import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 09:17
 **/
@Transactional
@Service
public class BindNoteServiceImpl implements IBindNoteService {

    @Autowired
    private BindNoteMapper bindNoteMapper;

    @Override
    public void insertBindNote(BindNote bindNote){
        bindNoteMapper.insertBindNote(bindNote);
    }

    @Override
    public List<BindNote> findByUsername(HashMap<String, Object> m){
        return bindNoteMapper.findByUsername(m);
    }


}
