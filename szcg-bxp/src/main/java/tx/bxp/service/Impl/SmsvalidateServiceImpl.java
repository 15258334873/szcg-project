package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.mapper.SmsvalidateMapper;
import tx.bxp.entity.SmsValidate;
import tx.bxp.service.ISmsvalidateService;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-08 10:32
 **/
@Transactional
@Service
public class SmsvalidateServiceImpl implements ISmsvalidateService {

    @Autowired
    private SmsvalidateMapper smsvalidateMapper;

    @Override
    public Integer InsertSmsvalidate(SmsValidate smsvalidate) {
        Integer i=smsvalidateMapper.insertSmsvalidate(smsvalidate);
        return i;
    }

    @Override
    public Integer UpdateSmsvalidate(SmsValidate smsvalidate) {
        Integer i=smsvalidateMapper.updateSmsvalidate(smsvalidate);
        return null;
    }

    @Override
    public SmsValidate getSmsvalidateByPhone(String phone) {
        SmsValidate smsvalidate=smsvalidateMapper.selectSmsvalidateByPhone(phone);
        return smsvalidate;
    }

    @Override
    public SmsValidate getSmsvalidate(String phone) {
        SmsValidate smsvalidate=smsvalidateMapper.selectSmsvalidateByPhone1(phone);
        return smsvalidate;
    }

}
