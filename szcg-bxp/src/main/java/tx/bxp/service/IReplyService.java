package tx.bxp.service;

import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.Preply;
import tx.bxp.entity.Reply;
import tx.bxp.util.ReplyUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-17
 **/

public interface IReplyService {

    void insertReply( Reply reply);

    ReplyUtil selectReplyByPId(HashMap<String, Object> map);
}
