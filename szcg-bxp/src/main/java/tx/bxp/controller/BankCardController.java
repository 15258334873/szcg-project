package tx.bxp.controller;

import org.springframework.web.bind.annotation.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import tx.bxp.entity.BankCard;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IBankCardService;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 银行卡管理模块
 * @author fyz
 * @date 2018-07-02
 */

@RestController
@RequestMapping("/BankCard")
public class BankCardController {

    @Autowired
    private IBankCardService iBankCardService;

    /**
     * 查询银行卡详情
     * @author FYZ
     * @date 2018/7/2 14:23
     * @param
     * @return
     */
    @PostMapping("/getBankCardinfodetail")
    public BankCard getUser(@Param("id") Integer id ) throws BxpException {
        try {
            if (id == null ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
          BankCard bankcard = iBankCardService.selectBankCardByid(id);
            return bankcard;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            System.out.println(id);
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查询userid查询多条银行卡
     * @author FYZ
     * @date 2018/7/2 14:23
     * @param
     * @return
     */
    @PostMapping("/getBankCardinfo")
    public List<BankCard> getUser(HttpServletRequest request) throws BxpException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String userid=null;
        String pagenum=null;
        String pagesize=null;
        try {
            userid=request.getParameter("userid");
            pagenum=request.getParameter("pagenum");
            pagesize=request.getParameter("pagesize");
            if (userid == null || "".equals(userid)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (pagenum==null || "".equals(pagenum)){
                pagenum="1";
            }
            if(pagesize==null || "".equals(pagesize)){
                pagesize="10";
            }
            pagenum=Integer.toString((Integer.valueOf(pagenum)-1)*Integer.valueOf(pagesize)+1);
            map.put("userid", userid);
            map.put("pagenum", Integer.valueOf(pagenum));
            map.put("pagesize", Integer.valueOf(pagesize));
            List<BankCard> list = iBankCardService.selectBankCardByUserid(map);
            return list;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
     }

     /**
     * 插入一条银行卡信息
     *
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @PostMapping("/insertBankCardinfo")
    public String insertBankCard(BankCard bankcard) throws BxpException {
        try {
            if (bankcard.getBankname() == null || bankcard.getBanknum() == null || bankcard.getUserid() == null || bankcard.getUsername() == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if(iBankCardService.selectBanknum(bankcard.getBanknum())>=1){
                throw BxpExceptionEnum.USER_EXIST.exception();
            }
            iBankCardService.insertBankCard(bankcard);
            return "银行卡添加成功";
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 更新一条银行卡信息
     *
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @GetMapping("/updatebankcardinfo")
    public String updateUser(BankCard bankcard) throws BxpException {
        try {
            if (bankcard.getBankname() == null || bankcard.getBanknum() == null || bankcard.getUserid() == null || bankcard.getUsername() == null) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            iBankCardService.updateBankCard(bankcard);
            return "更新银行卡信息成功";
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 删除一条银行卡信息
     *
     * @param
     * @return
     * @author FYZ
     * @date 2018/7/2 14:23
     */
    @GetMapping("/deletebankcareinfo")
    public String deleteBankCard(@Param("id") Integer id) throws BxpException {
        try {
            if (id == 0 || id == null || "".equals(id)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            iBankCardService.deleteBankCard(id);
            return "删除银行成功";
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }
}