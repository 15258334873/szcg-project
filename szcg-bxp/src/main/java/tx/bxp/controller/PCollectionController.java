package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.entity.PCollection;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IPCollectionService;
import tx.bxp.util.PageUtil;
import tx.framework.api.exception.ApiException;
import tx.framework.api.exception.ApiExceptionEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: tx.framework
 * @description: 用户收藏
 * @author:pck
 * @create: 2018-07-02 14:02
 **/

@RestController
@RequestMapping(value = "/pcollection",method=RequestMethod.POST)
public class PCollectionController {

    @Autowired
    private IPCollectionService pCollectionService;

    /**
     * 保存收藏信息
     * @author chenmc
     * @date 2018/7/2 14:10
     * @param
     * @return
     * @RequestHeader(required = false,value = "Authorization") String Authorization,
     */
    @PostMapping(value = "/add")
    public void addpcollection(HttpServletRequest request,HttpServletResponse response) throws BxpException {

        //案件Id或资讯Id
        String pId=null;
        //用户Id
        String userId =null;
        //类型
        String type=null;
        try{
            pId=request.getParameter("pId");
            userId=request.getParameter("userId");
            type=request.getParameter("type");
           if((pId==null || "".equals(pId)) || (type==null || "".equals(type)) || (userId==null || "".equals(userId))){
              throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            PCollection pcollection=new PCollection();
            pcollection.setType(Integer.valueOf(type));
            pcollection.setPid(Integer.valueOf(pId));
            pcollection.setUserid(Integer.valueOf(userId));
            pCollectionService.savepcollection(pcollection);
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 删除收藏纪录
     * @author chenmc
     * @date 2018/7/2 14:25
     * @param
     * @return
     */
    @PostMapping(value ="/remoeve")
    public void remoevepcollection(HttpServletRequest request) throws BxpException {
        //案件Id或资讯Id
        String pId = null;
        //用户Id
        String userId = null;
        //类型
        String type = null;

        try {

            pId = request.getParameter("zId");
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            if (pId == null || "".equals(pId)  || userId == null || "".equals(userId)  || type == null || "".equals(type) ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("pId",pId);
            map.put("userId",userId);
            map.put("type",userId);
            pCollectionService.remoevepcollection(map);

        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查询收藏列表
     * @author chenmc
     * @date 2018/7/2 14:29
     * @param
     * @return
     */
  @PostMapping(value = "/list")
    public ArrayList<BxpProjectList> ListPcollection(HttpServletRequest request,HttpServletResponse response) throws BxpException{
        String userId=null;
        String Page=null;
        String PageSize=null;
        try{
            userId=request.getParameter("userId");
            Page=request.getParameter("Page");
            PageSize=request.getParameter("PageSize");
            if(userId==null || "".equals(userId)){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if(Page==null || "".equals(Page)){
                Page="1";
            }
            if(PageSize==null || "".equals(PageSize)){
                PageSize="10";
            }
            Integer [] i=PageUtil.paging(Page,PageSize);
            HashMap<String,Integer> map=new HashMap<String,Integer>();
            map.put("userId",Integer.valueOf(userId));
            map.put("Page",i[0]);
            map.put("PageSize",i[1]);
            ArrayList<BxpProjectList> bxpProjectList= pCollectionService.ListPCollection(map);
            return bxpProjectList;
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }
}
