package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.ILikeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: szcg-project
 * @description: 点赞功能
 * @author:pck
 * @create: 2018-08-03 14:20
 **/
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    /**
     * 用户点赞
     * @author chenmc
     * @date 2018/8/6 13:22
     * @param
     * @return
     */
    @PostMapping(value ="/add")
    public String addLike (HttpServletRequest request, HttpServletResponse response) throws BxpException {
        //用户Id
        String userId = null;
        //类型
        String type = null;
        //案件或资讯Id
        String pId=null;
        try {
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            pId = request.getParameter("pId");

            if ((userId==null || "".equals(userId)) || (type==null || "".equals(type)) || (pId==null || "".equals(pId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            likeService.save(Integer.valueOf(userId),Integer.valueOf(pId),Integer.valueOf(type));
            return "点赞成功";
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 取消点赞
     * @author chenmc
     * @date 2018/8/6 13:23
     * @param
     * @return
     */
    @PostMapping(value ="/remove")
    public String removeLike (HttpServletRequest request, HttpServletResponse response) throws BxpException {

        //用户Id
        String userId = null;
        //类型
        String type = null;
        //案件或资讯Id
        String pId=null;
        try {
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            pId = request.getParameter("pId");
            if ((userId==null || "".equals(userId)) || (type==null || "".equals(type)) || (pId==null || "".equals(pId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            likeService.remove(Integer.valueOf(userId),Integer.valueOf(pId),Integer.valueOf(type));
            return "取消点赞";
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }
}
