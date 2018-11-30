package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BindNote;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.mapper.SysAreaServiceMapper;
import tx.bxp.service.IBindNoteService;
import tx.bxp.service.ISysAreaService;

import java.util.ArrayList;
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
public class SysAreaServiceImpl implements ISysAreaService {

    @Autowired
    private SysAreaServiceMapper sysAreaServiceMapper;

    @Override
    public ArrayList<HashMap<String, Object>> selectarea() {
        return sysAreaServiceMapper.selectarea();
    }

    @Override
    public ArrayList<HashMap<String, Object>> getway(Integer id) {
        return sysAreaServiceMapper.selectway(id);
    }

    @Override
    public Integer addevaluation(HashMap<String, Object> map) {
        return sysAreaServiceMapper.insertevaluation(map);
    }
}
