package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.BindNote;
import tx.bxp.mapper.BankCardMapper;
import tx.bxp.entity.BankCard;
import tx.bxp.mapper.BindNoteMapper;
import tx.bxp.service.IBankCardService;
import java.util.List;
import java.util.HashMap;


/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-02 16:54
 **/
@Service
@Transactional
public class BankCardServiceImpl implements IBankCardService {

    @Autowired
    private BankCardMapper BankCard;

    @Autowired
    private BindNoteMapper bindNoteMapper;

    @Override
    public List<tx.bxp.entity.BankCard> selectBankCardByUserName(String UserName) {
        return BankCard.selectBankCardByUserName(UserName);
    }

    //通过userid查询银行卡信息(多张)
    @Override
    public List<BankCard> selectBankCardByUserid(HashMap<String, Object> map) {
        return BankCard.selectBankCardByUserid(map);
    }

   //查询详情
    @Override
    public BankCard selectBankCardByid(int id) {

        return BankCard.selectBankCardByid(id);
    }

     //插入银行卡信息
    @Override
    public void insertBankCard(BankCard bankcard) {
        BankCard.insertBankCard(bankcard);
    }
    //更新银行卡信息
    @Override
    public void updateBankCard(BankCard bankcard){

        BankCard.updateBankCard(bankcard);
    }

    //删除银行卡信息
    @Override
    public void deleteBankCard(int id){
        BankCard.deleteBankCard(id);
    }


    //查询银行卡号码
    @Override
    public Integer selectBanknum(String banknum){
       return   BankCard.selectBanknum(banknum);
    }

    @Override
    public Integer removeBankCard(String username) {
        // TODO Auto-generated method stub
        return BankCard.removeBankCard(username);
    }

    @Override
    public tx.bxp.entity.BankCard getBanknumByUserName(String userLoginname) {
        return BankCard.selectBanknumByUserName(userLoginname);
    }

    @Override
    public void binding(BindNote bindNote, tx.bxp.entity.BankCard bankCard) {
        BankCard.insertBankCard(bankCard);
        bindNoteMapper.insertBindNote(bindNote);
    }

    @Override
    public void unBinding(BindNote bindNote,String CellNum) {
        BankCard.removeBankCard(CellNum);
        bindNoteMapper.insertBindNote(bindNote);
    }
}

