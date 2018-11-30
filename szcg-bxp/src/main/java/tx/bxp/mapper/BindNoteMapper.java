package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.BindNote;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface BindNoteMapper {
    void insertBindNote(BindNote bindNote);
    List<BindNote> findByUsername(HashMap<String, Object> m);
}
