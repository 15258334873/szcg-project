package tx.bxp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IbxpProiService;
import tx.bxp.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import tx.framework.data.cache.RedisCache;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: tx.framework
 * @description: 案件管理
 * @author:fyz
 * @create: 2018-07-16 14:02
 **/

@RestController
@RequestMapping(value = "/bxpproc", method = RequestMethod.POST)
public class BxpProcController {

    @Autowired
    private IbxpProiService bxpProiService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 案件上报（我要举报）
     *
     * @param request
     * @param Authorization
     * @param files
     * @return
     * @RequestHeader(required = false, value = "Authorization") String Authorization,
     */

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public void addbxpproc(HttpServletRequest request, @RequestParam(value = "projfile") MultipartFile[] files) throws BxpException {
        //地址
        String address =null;
        //经度
        String lon = null;
        //纬度
        String lat = null;
        //标题
        String title = null;
        //内容
        String moment = null;
        //用户Id
        String userId = null;

        String seq=redisCache();

        try {
            address = request.getParameter("address");
            lon = request.getParameter("lon");
            lat = request.getParameter("lat");
            title = request.getParameter("title");
            moment = request.getParameter("moment");
            userId = request.getParameter("userId");
            if ((files==null) || (moment == null || "".equals(moment)) || (address == null || "".equals(address)) || (lon == null || "".equals(lon)) || (lat == null || "".equals(lat)) || (userId == null || "".equals(userId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            bxpProiService.savebxpPro(address, lon, lat, moment, userId, files,seq);
        }catch(BxpException e){
            throw e;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查看案件列表
     * @author chenmc  
     * @date 2018/7/30 13:37
     * @param   
     * @return   
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public  ArrayList<BxpProjectList> ListBxpProject(HttpServletRequest request) throws BxpException  {
        String Page=null;
        String PageSize=null;
        try {
            Page = request.getParameter("Page");
            PageSize = request.getParameter("PageSize");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            ArrayList<BxpProjectList> bxpProjectList = bxpProiService.BxpProjectList();
            return bxpProjectList;
        }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查看案件详情
     * @author chenmc
     * @date 2018/8/6 13:21
     * @param
     * @return
     */
    @PostMapping(value = "/get")
    public BxpProject getBxpProject(HttpServletRequest request, HttpServletResponse response) throws BxpException{
        String id=null;
        String type=null;
        String uesrId=null;
        try{
            id=request.getParameter("id");
            type=request.getParameter("type");
            uesrId=request.getParameter("uesrId");
            if((id==null || "".equals(id)) || (type==null || "".equals(type))){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if(uesrId==null || "".equals(uesrId)){
                uesrId="0";
            }
            BxpProject bxpProject=bxpProiService.getBxpProject(Integer.valueOf(id),Integer.valueOf(type),Integer.valueOf(uesrId));
            return  bxpProject;
         }catch(BxpException e){
             throw e;
         }catch(Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
         }
    }

//        @PostMapping(value = {"/getredis"})
        public String redisCache() {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateStr = dateFormat.format(new Date());
            String seq = dateStr + new DecimalFormat("00000").format(redisCache.getSequence("projectNumber:" + dateStr, Duration.ofDays(1).plusHours(1)));
            return seq ;
        }

}
