package tx.bxp.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import tx.bxp.dto.Preply;
import tx.bxp.entity.Reply;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.Impl.ReplyServiceImpl;
import tx.bxp.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 回复管理
 *
 * @author FYZ
 * @date 2018-7-17
 */
@RestController
@RequestMapping("/Reply")

public class ReplyController {

    @Autowired
    private ReplyServiceImpl replyService;

    /**
     * 新增回复
     * @author FYZ
     * @date 2018/7/17 14:23
     * @param
     * @return
     */
    @PostMapping("/insertReply")
    public String addreply( HttpServletRequest request) throws BxpException {
        String userid = null;
        String projid = null;
        //String replystatus = null;
        String content = null;
        String type = null;
        try {
            userid = request.getParameter("userid");
            projid = request.getParameter("projid");
            content = request.getParameter("content");
            type = request.getParameter("type");
            if ((userid == null || "".equals(userid)) || (projid == null || "".equals(projid)) || (content == null || "".equals(content)) || (type == null || "".equals(type))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Reply reply=new Reply();
            reply.setContent(content);
            reply.setUserid(Integer.valueOf(userid));
            reply.setProjid(Integer.valueOf(projid));
            reply.setType(Integer.valueOf(type));
            replyService.insertReply(reply);
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
        return "回复成功";
    }

    /**
     * 查询评论列表
     * @author chenmc
     * @date 2018/8/3 13:40
     * @param
     * @return
     */
   /* @PostMapping("/list")
    public ArrayList<Preply> listReply(HttpServletRequest request) throws BxpException {
        String projid = null;
        String type = null;
        String Page=null;
        String PageSize=null;
        try {
            projid = request.getParameter("projid");
            type = request.getParameter("type");
            Page = request.getParameter("Page");
            PageSize = request.getParameter("PageSize");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }

            if ((projid == null || "".equals(projid)) || (type == null || "".equals(type)))  {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Preply preply=new Preply();
            preply.setPid(Integer.valueOf(projid));
            preply.setType(Integer.valueOf(type));

            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            map.put("pid", Integer.valueOf(projid));
            map.put("type", Integer.valueOf(type));

            ArrayList<Preply> PreplyList=  replyService.selectReplyByPId(map);
            return PreplyList;
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }*/




}


