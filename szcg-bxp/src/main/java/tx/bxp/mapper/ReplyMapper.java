package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.dto.Preply;
import tx.bxp.entity.Reply;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: szcg-project
 * @description:
 * @author:fyz
 * @create: 2018-07-17 10:02
 **/
@Mapper
public interface ReplyMapper {

    void insertReply(Reply reply);

    ArrayList<Preply> selectReplyByPId( HashMap<String, Object> map );


}
