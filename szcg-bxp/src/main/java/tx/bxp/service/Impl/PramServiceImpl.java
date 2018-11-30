package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.mapper.PramMapper;
import tx.bxp.service.IPramService;
import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-10-15 11:44
 **/

@Service
@Transactional
public class PramServiceImpl implements IPramService {

    @Autowired
    private PramMapper pramMapper;

    @Override
    public HashMap<String, Object> getPram(String pram) {
        return pramMapper.selectpram(pram);
    }
}
