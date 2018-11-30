package tx.bxp.service;

import tx.bxp.entity.BindNote;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/

public interface IBindNoteService {

    //添加绑定或解绑日志
    public void insertBindNote(BindNote bindNote);

    //查询用户绑定或解绑日志
    public List<BindNote> findByUsername(HashMap<String, Object> m);
}
