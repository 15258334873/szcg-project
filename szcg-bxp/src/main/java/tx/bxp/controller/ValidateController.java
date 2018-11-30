package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IUserinfoService;
import tx.bxp.service.IValidateService;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @program: szcg-project
 * @description: 验证码
 * @author:pck
 * @create: 2018-08-08 14:48
 **/
@RequestMapping("/validate")
@RestController
public class ValidateController {

    @Autowired
    private IValidateService validateService;

    @Autowired
    private IUserinfoService userinfoService;

    @PostMapping("/get")
    public String  getValidate(HttpServletRequest request) throws BxpException
    {
        String Phone =null;
        StringBuffer validate = new StringBuffer();
        try{
            Phone=request.getParameter("Phone");
            if(Phone==null || "".equals(Phone)){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            //生成码证码
            Random d = new Random();
              for (int i = 0; i < 6; i++) {
                validate = validate.append(d.nextInt(10));
            }
            boolean isValidatePhone= validateService.isValidatePhone(Phone);
              if(isValidatePhone){
                  //更新
                  validateService.editValidate(Phone,validate.toString());
              }else{
                  //插入
                  validateService.addValidate(Phone,validate.toString());
              }
              return "验证码获取成功";

        }catch (BxpException e){
            throw e;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping("/regain")
    public String  RegainValidate(HttpServletRequest request) throws BxpException
    {
        String Phone =null;
        StringBuffer validate = new StringBuffer();
        try{
            Phone=request.getParameter("Phone");
            if(Phone==null || "".equals(Phone)){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            //生成码证码
            Random d = new Random();
            for (int i = 0; i < 6; i++) {
                validate = validate.append(d.nextInt(10));
            }
            boolean LoginnameExist= userinfoService.isLoginnameExist(Phone);

            if(LoginnameExist) {
                //更新
                validateService.editValidate(Phone, validate.toString());
            }else{
                throw BxpExceptionEnum.USER_NOT_EXIST.exception();
            }
            return "验证码获取成功";

        }catch (BxpException e){
            throw e;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }





}
