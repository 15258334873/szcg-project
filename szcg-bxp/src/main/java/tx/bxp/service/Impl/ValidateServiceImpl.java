package tx.bxp.service.Impl;

import com.mtmasws.service.MasWSProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mtmasws.service.MasWSProxy;
import tx.bxp.entity.SmsValidate;
import tx.bxp.entity.Smslog;
import tx.bxp.mapper.SmslogMapper;
import tx.bxp.mapper.ValidateMapper;
import tx.bxp.service.IValidateService;
import tx.bxp.util.SMSUtil;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-08-09 09:38
 **/
@Service
@Transactional
public class ValidateServiceImpl implements IValidateService {

    @Autowired
    private ValidateMapper validateMapper;

    @Autowired
    private SmslogMapper smslogMapper;

    @Override
    public boolean isValidatePhone(String Phone) {

       String Validate= validateMapper.SelectValidatePhone(Phone);

       if(Validate==null){
           return false;
       }else{
           return true;
       }
    }

    @Override
    public void addValidate(String Phone,String validate) throws Exception {


        SmsValidate smsValidate=new SmsValidate();
        smsValidate.setPhone(Phone);
        smsValidate.setValidate(validate);
        validateMapper.InsertValidate(smsValidate);



        Smslog smslog=new Smslog();
        smslog.setMsg(validate);
        smslog.setPhone(Phone);
        smslogMapper.InsertSmsLog(smslog);

        SMSUtil smsUtil=new SMSUtil();
        String  ss = smsUtil.send(Phone,validate);

        if("000".equals(ss)){

        }else{
           int i= 1/0;
        }


    }

    @Override
    public void editValidate(String Phone,String validate) throws Exception {

        SmsValidate smsValidate=new SmsValidate();
        smsValidate.setPhone(Phone);
        smsValidate.setValidate(validate);
        validateMapper.UpdateValidate(smsValidate);

        Smslog smslog=new Smslog();
        smslog.setMsg(validate);
        smslog.setPhone(Phone);
        smslogMapper.InsertSmsLog(smslog);


        SMSUtil smsUtil=new SMSUtil();
        String  ss = smsUtil.send(Phone,validate);

        if("000".equals(ss)){

        }else{
            int i= 1/0;
        }

    }

    @Override
    public String getValidateByPhone(String Phone) {

        String Validate= validateMapper.SelectValidateByPhone(Phone);

        return Validate;

    }
}
