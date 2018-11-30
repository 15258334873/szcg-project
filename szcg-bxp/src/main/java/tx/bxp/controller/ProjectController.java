package tx.bxp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.NewThreadAction;
import tx.bxp.dto.*;
import tx.bxp.dto.BxpProject;
import tx.bxp.entity.*;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.*;
import tx.bxp.service.Impl.ReplyServiceImpl;
import tx.bxp.util.Base64Coder;
import tx.bxp.util.LikeUtil;
import tx.bxp.util.PageUtil;
import tx.bxp.util.ReplyUtil;
import tx.framework.data.cache.RedisCache;
import tx.framework.security.util.LoginUserUtil;
import tx.framework.web.json.bxp.ReturnObj;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-07 12:50
 **/
@RestController
@RequestMapping("/bxpproj")
public class ProjectController {

    //案件上报
    @Autowired
    private IbxpProiService bxpProiService;

    //redis
    @Autowired
    private RedisCache redisCache;

    //任务
    @Autowired
    private ITaskService iTaskService;

    //回复
    @Autowired
    private ReplyServiceImpl replyService;

    //收藏
    @Autowired
    private IPCollectionService pCollectionService;

    //点赞
    @Autowired
    private ILikeService likeService;

    @Autowired
    private IInfoService infoService;

    //用户
    @Autowired
    private IUserinfoService userinfoService;

    //满意评价
    @Autowired
    private ISysAreaService sysAreaService;

    //奖励
    @Autowired
    private IbxpProjAwardService ibxpProjAwardService;

    @Autowired
    private IPramService iPramService;

    //老版数据调用地址
    @Value("${bxp.DataUrl}")
    private String DataUrl;


    /**
     * redis 缓存
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/28 15:39
     */
    public String redisCache() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(new Date());
        String seq = dateStr + new DecimalFormat("00000").format(redisCache.getSequence("projectNumber:" + dateStr, Duration.ofDays(1).plusHours(1)));
        return seq;
    }

    /**
     * 上报前验证功能
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/7 12:57
     * @RequestHeader String Authorization
     */
    @PostMapping(value = "/Validate")
    public ReturnObj Validate(HttpServletRequest request) throws BxpException {
        String userId = null;
        try {
            userId = request.getParameter("userId");
            if (userId == null || "".equals(userId)) {
                throw BxpExceptionEnum.LOGIN_DATE.exception();
            }
          /*  String deAuth = Base64Coder.decodeString(Authorization);
            String[] userInfo = deAuth.split("[:]");
            if (userInfo.length != 2) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String UserId = userInfo[0].toString();*/

            if (userId == null || "".equals(userId)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

            Userinfo userinfo = userinfoService.selectUserinfoByID(Integer.valueOf(userId));

            if (userinfo == null) {
                throw BxpExceptionEnum.USER_NO_EXIST.exception();
            }

            if (userinfo.getIsdel().equals(1)) {
                throw BxpExceptionEnum.DISABLE_ACCOUNT.exception();
            }

            Integer projcount = bxpProiService.getuserIdCount(Integer.valueOf(userId));

            if (projcount >= 50) {
                throw BxpExceptionEnum.REPORTED_LIMIT_NUMBER.exception();
            }

            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("可以上报案件");
            return returnObj;
            //mkldnreturn "可以上报案件";
        } catch (BxpException E) {
            throw E;
        } catch (Exception EX) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 案件上报（我要举报）
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/7 12:57
     * @RequestHeader(required = false, value = "Authorization") String Authorization,
     */
    @PostMapping(value = "/report")
    public ReturnObj report(HttpServletRequest request, @RequestParam(value = "projfile", required = false) MultipartFile[] files) throws BxpException {
        //地址
        String address = null;
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
        //类型
        String type = null;

        String seq = redisCache();
        try {
            address = request.getParameter("address");
            lon = request.getParameter("lon");
            lat = request.getParameter("lat");
            title = request.getParameter("title");
            moment = request.getParameter("mome");
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            if ((type == null || "".equals(type)) || (files == null) || (moment == null || "".equals(moment)) || (address == null || "".equals(address)) || (lon == null || "".equals(lon)) || (lat == null || "".equals(lat)) || (userId == null || "".equals(userId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

            if (type.equals("0")) {

                bxpProiService.savebxpPro(address, lon, lat, moment, userId, files, seq);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(null);
                returnObj.setFlag("案件上报成功");
                return returnObj;
            } else {
                Info info = new Info();
                info.setAddress(address);
                info.setLon(lon);
                info.setLat(lat);
                info.setTitle(title);
                info.setContent(moment);
                info.setAudit_status(0);
                info.setIsdel(0);
                info.setInfo_type(Integer.valueOf(type));
                info.setUserid(Integer.valueOf(userId));
                infoService.insertInfo(info, files);
                // bxpProiService.savebxpPro(address, lon, lat, moment, userId, files, seq);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(null);
                returnObj.setFlag("案件上报成功");
                return returnObj;
            }

        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 抢单列表（我要核实列表）
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/11 15:40
     */
    @PostMapping(value = "/sendProList")
    public ReturnObj sendProList(HttpServletRequest request) throws BxpException {
        String Page = null;
        String PageSize = null;
        try {
            Page = request.getParameter("beginIndex");
            PageSize = request.getParameter("endIndex");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            List<MyPTask> list = iTaskService.ListPTask(map);
            ReturnObj returnObj = new ReturnObj();
            if (list == null || list.size() == 0) {
                returnObj.setObj(list);
                returnObj.setFlag("没有抢单数据");
            } else {
                returnObj.setObj(list);
                returnObj.setFlag("获取抢单列表成功");
            }
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 我要抢单
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/11 15:58
     */
    @PostMapping(value = "/getPro")
    public ReturnObj sendPro(HttpServletRequest request) throws BxpException {
        String id = request.getParameter("rId");
        String uid = request.getParameter("userId");
        LocalDateTime start = LocalDateTime.now();
        try {
            if ((id == null || "".equals(id)) || (uid == null || "".equals(uid))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if(redisCache.get(id) == null) {
                // 未锁定
                PTask task = iTaskService.GetPTask(Integer.valueOf(id));
                if (task == null) {
                    throw BxpExceptionEnum.LOSE.exception();
                }else{
                    Duration expireTime = Duration.ofMinutes(30);
                    redisCache.set(id,1,expireTime);
                }
                if (task.getUserid() != null) {
                    throw BxpExceptionEnum.LOOTED.exception();
                }

                task.setUserid(Integer.valueOf(uid));
                task.setId(Integer.valueOf(id));
                iTaskService.EditTask(task);
                ReturnObj returnObj = new ReturnObj();
                returnObj.setObj(null);
                returnObj.setFlag("抢单成功");
                return returnObj;
            }else{
                // 已锁定
                throw BxpExceptionEnum.LOSE.exception();
            }
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 首页案件列表
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/11 16:02
     */
    @PostMapping(value = "/getProList")
    public ReturnObj getProList(HttpServletRequest request) throws BxpException {
        String Page = null;
        String PageSize = null;
        String type;
        try {
            Page = request.getParameter("pagenum");
            PageSize = request.getParameter("everypage");
            type = request.getParameter("type");
            if (type == null || "".equals(type)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            Integer[] i = PageUtil.paging(Page, PageSize);
            ArrayList<BxpProjectList> bxpProjectList = new ArrayList<>();
            if (type.equals("0")) {
                ArrayList<BxpProjectList> bxpProjectList1 = bxpProiService.BxpProjectList();
                if (bxpProjectList1 != null) {
                    int sum = i[0] + i[1];
                    if (i[0] > bxpProjectList1.size()) {
                    } else {
                        if (sum > bxpProjectList1.size()) {
                            for (int j = i[0]; j < bxpProjectList1.size(); j++) {
                                bxpProjectList.add(bxpProjectList1.get(j));
                            }
                        } else {
                            for (int j = i[0]; j < sum; j++) {
                                bxpProjectList.add(bxpProjectList1.get(j));
                            }
                        }
                    }
                }
            } else {
                ArrayList<BxpProjectList> bxpProjectList1=null;
                switch (Integer.valueOf(type)){
                    case 4:
                        bxpProjectList1= infoService.searchInfoList4(Integer.valueOf(type));
                        break;
                    case 5:
                        bxpProjectList1 = infoService.searchInfoList5(Integer.valueOf(type));
                        break;
                    case 6:
                        bxpProjectList1 = infoService.searchInfoList6(Integer.valueOf(type));
                        break;
                    case 7:
                        bxpProjectList1 = infoService.searchInfoList7(Integer.valueOf(type));
                        break;
                    default:
                        break;
                }

                if (bxpProjectList1 != null) {
                    int sum = i[0] + i[1];
                    if (i[0] > bxpProjectList1.size()) {
                    } else {
                        if (sum > bxpProjectList1.size()) {
                            for (int j = i[0]; j < bxpProjectList1.size(); j++) {
                                bxpProjectList.add(bxpProjectList1.get(j));
                            }
                        } else {
                            for (int j = i[0]; j < sum; j++) {
                                bxpProjectList.add(bxpProjectList1.get(j));
                            }
                        }
                    }
                }
            }
            ReturnObj returnObj = new ReturnObj();
            if (bxpProjectList == null || bxpProjectList.size() == 0) {
                returnObj.setObj(bxpProjectList);
                returnObj.setFlag("没有案件数据");
            } else {
                returnObj.setObj(bxpProjectList);
                returnObj.setFlag("获取案件列表成功");
            }
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 我的案件列表
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/11 16:06
     * @RequestHeader(required = false, value = "Authorization") String Authorization
     */
    @PostMapping(value = "/myProList")
    public ReturnObj myProList(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String userId = null;
        String state = null;
        String Page = null;
        String PageSize = null;
        try {
            userId = request.getParameter("userId");
            state = request.getParameter("state");
            if (userId == null || "".equals(userId)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Page = request.getParameter("everypage");
            PageSize = request.getParameter("pagenum");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }

            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("state", state);
/*            map.put("Page", i[0]);
            map.put("PageSize", i[1]);*/
            map.put("Page", 0);
            map.put("PageSize",1000);
            ArrayList<MyBxpProject> bxpProjectList = bxpProiService.MyBxpProjectList(map);//获取新的数据

            //获取老数据开始

            HttpClient httpClient = new DefaultHttpClient();
            String url =DataUrl+"/proj/myProList?userId="+userId+"&type=0&state="+state+"&everypage=1&pagenum=100";

            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);

            int statusCode = resp.getStatusLine().getStatusCode();
            if(statusCode==200){

                BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
              String content = sb.toString();

                JSONObject jsonObject = JSON.parseObject(content);
                String returnFlag=(String) jsonObject.get("returnFlag");
                if(returnFlag==null || returnFlag.equals("1")){
                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                }else{
                    List<returnObj> returnObjs= JSON.parseArray(jsonObject.get("returnObj").toString(),returnObj.class);
                    for (returnObj Obj: returnObjs) {
                        MyBxpProject myBxpProject = new MyBxpProject();
                        myBxpProject.setAddress(Obj.getAddress());
                        myBxpProject.setAudiourl(Obj.getAudiourl());
                        myBxpProject.setBrowsenumber(0);
                        myBxpProject.setCreationTime(new Date(Long.valueOf(Obj.getCudate().toString())));
                        myBxpProject.setId(Obj.getId());
                        myBxpProject.setFid(Obj.getLon() + "," + Obj.getLat());
                        myBxpProject.setImgurl(Obj.getImgurl());
                        myBxpProject.setMome(Obj.getMome());
                        myBxpProject.setLikenumber(Obj.getLikesum());
                        myBxpProject.setMoney(new BigDecimal(Obj.getMoney()));
                        myBxpProject.setProjcode(Obj.getProjcode());
                        myBxpProject.setScore(Obj.getScore().toString());
                        myBxpProject.setState(Obj.getState());
                        myBxpProject.setTitle(Obj.getTitle());
                        myBxpProject.setType(Obj.getType());
                        myBxpProject.setVideourl(Obj.getVideourl());
                        bxpProjectList.add(myBxpProject);

                    }
                }
            }else{
                throw BxpExceptionEnum.REQUEST_FEILED.exception();
            }
            //获取老数据结束

            ReturnObj returnObj = new ReturnObj();

            if(bxpProjectList==null || bxpProjectList.size()< i[0]){
                ArrayList<MyBxpProject> bxpProjectList1=new ArrayList<>();
                returnObj.setObj(bxpProjectList1);
                returnObj.setFlag("没有案件数据");
            }

            if( bxpProjectList.size()>i[0]  && ( bxpProjectList.size()> (i[0]+i[1]))){
                ArrayList<MyBxpProject> bxpProjectList1=new ArrayList<>();
                for(int j= i[0];j<(i[0]+i[1]);j++){
                    bxpProjectList1.add(bxpProjectList.get(j));
                }
                returnObj.setObj(bxpProjectList1);
                returnObj.setFlag("获取案件列表成功");
            }
            if( bxpProjectList.size()>i[0]  && ( bxpProjectList.size()< (i[0]+i[1]))){
                ArrayList<MyBxpProject> bxpProjectList1=new ArrayList<>();
                for(int j= i[0];j<bxpProjectList.size();j++){
                    bxpProjectList1.add(bxpProjectList.get(j));
                }
                returnObj.setObj(bxpProjectList1);
                returnObj.setFlag("获取案件列表成功");
            }
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();;
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


    /**
     * 我的核实列表
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/10/8 10:30
     */
    @PostMapping(value = "/mysendProList")
    public ReturnObj mysendProList(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String userId = null;
        String ismake = null;
        String Page = null;
        String PageSize = null;
        try {
            userId = request.getParameter("userId");
            ismake = request.getParameter("ismake");
            if (userId == null || "".equals(userId)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Page = request.getParameter("pagenum");
            PageSize = request.getParameter("everypage");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            Integer[] i = PageUtil.paging(Page, PageSize);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            map.put("userId", userId);
            map.put("ismake", ismake);

            List<MyPTask> list = iTaskService.MyListPTask(map);

            ReturnObj returnObj = new ReturnObj();
            if (list == null || list.size() == 0) {
                returnObj.setObj(list);
                returnObj.setFlag("没有抢单数据");
            } else {
                returnObj.setObj(list);
                returnObj.setFlag("获取我的核实列表成功");
            }
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 核实上报
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/10/9 9:10
     */
    @PostMapping(value = "/qTask")
    public ReturnObj qTask(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "projfile", required = false) MultipartFile[] files) throws BxpException {
        String rId = request.getParameter("Awardid");
        String pId = request.getParameter("pId");
        String title = "";
        String address = request.getParameter("address");
        String lon = request.getParameter("lon");
        String lat = request.getParameter("lat");
        String mome = request.getParameter("mome");
        String userId = request.getParameter("userId");

        try {
            if ((rId == null) || (rId.equals("")) || (pId == null) || (pId.equals("")) || (address == null) ||
                    (address.equals("")) || (lon == null) || (lon.equals("")) || (lat == null) || (lat.equals("")) ||
                    (mome == null) || (mome.equals("")) || (userId == null) || (userId.equals("")) ||
                    (files == null)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }

            Userinfo userinfo = userinfoService.selectUserinfoByID(Integer.parseInt(userId));

            if (userinfo == null) {
                throw BxpExceptionEnum.USER_NO_EXIST.exception();
            }

            PTask task = iTaskService.GetPTask1(Integer.valueOf(rId));
            if (task == null) {
                throw BxpExceptionEnum.TASK_NO.exception();
            }
            if (task.getIsfinish() != 0) {
                throw BxpExceptionEnum.TASK_NO.exception();
            }


            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("rId", rId);
            map.put("pId", pId);
            map.put("address", address);
            map.put("lon", lon);
            map.put("lat", lat);
            map.put("mome", mome);
            map.put("userId", userId);

            iTaskService.qTask(map, files);

            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("核实上报成功");

            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


    /**
     * 案件回复
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/12 14:37
     */
    @PostMapping(value = "/ordinaryReport")
    public ReturnObj ordinaryReport(HttpServletRequest request, HttpServletResponse response) throws BxpException {

        ReturnObj returnObj = new ReturnObj();
        String address = null;
        String lon = null;
        String lat = null;
        String mome = null;
        String userId = null;
        Integer parentId = null;
        String pId = null;
        String type = null;
        try {
            address = request.getParameter("address");
            lon = request.getParameter("lon");
            lat = request.getParameter("lat");
            mome = request.getParameter("mome");
            userId = request.getParameter("userId");
            pId = request.getParameter("pId");
            type = request.getParameter("type");
            if ((address == null) || ("".equals(address)) || (lon == null) || ("".equals(lon)) || (lat == null)
                    || ("".equals(lat)) || (mome == null) || ("".equals(mome)) || (userId == null) || type == null || "".equals(type)
                    || ("".equals(userId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            parentId = Integer.valueOf(pId);
            if(parentId>9999999) {
                Reply reply = new Reply();
                reply.setContent(mome);
                reply.setUserid(Integer.valueOf(userId));
                reply.setProjid(parentId);
                reply.setType(Integer.valueOf(type));
                reply.setAddress(address);
                reply.setFid(lon + "," + lat);
                replyService.insertReply(reply);
            }else {
                //向老平台插入数据
                HttpClient httpClient = new DefaultHttpClient();
                String url = DataUrl + "/proj/myProList?address=" + address + "&lon=" + lon + "&lat=" + lat + "&mome=" + mome + "&userId=" + userId + "&pId=" + pId;
                HttpPost httpPost = new HttpPost(url);
                HttpResponse resp = httpClient.execute(httpPost);
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line + NL);
                    }
                    in.close();
                    String content = sb.toString();
                    System.out.println(content);
                    JSONObject jsonObject = JSON.parseObject(content);
                    String returnFlag = (String) jsonObject.get("returnFlag");
                    if (returnFlag == null || returnFlag.equals("1")) {
                        throw BxpExceptionEnum.REQUEST_FEILED.exception();
                    }
                } else {
                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                }
            }
            returnObj.setObj(null);
            returnObj.setFlag("回复成功");
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 保存收藏信息
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/7/2 14:10
     * @RequestHeader(required = false,value = "Authorization") String Authorization,
     */
    @PostMapping(value = "/shoucang")
    public ReturnObj shoucang(HttpServletRequest request, HttpServletResponse response) throws BxpException {

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
            if ((pId == null || "".equals(pId)) || (type == null || "".equals(type)) || (userId == null || "".equals(userId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            PCollection pcollection = new PCollection();
            pcollection.setType(Integer.valueOf(type));
            pcollection.setPid(Integer.valueOf(pId));
            pcollection.setUserid(Integer.valueOf(userId));
            pCollectionService.savepcollection(pcollection);

            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("收藏成功");

            return returnObj;


        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 删除收藏纪录
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/7/2 14:25
     */
    @PostMapping(value = "/delcang")
    public ReturnObj delcang(HttpServletRequest request) throws BxpException {

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
            if (pId == null || "".equals(pId) || userId == null || "".equals(userId) || type == null || "".equals(type)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("pId", pId);
            map.put("userId", userId);
            map.put("type", type);
            pCollectionService.remoevepcollection(map);

            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("取消收藏成功");

            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查询收藏列表
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/7/2 14:29
     */
    @PostMapping(value = "/mycang")
    public ReturnObj mycang(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String userId = null;
        String Page = null;
        String PageSize = null;
        try {
            userId = request.getParameter("userId");
            Page = request.getParameter("everypage");
            PageSize = request.getParameter("pagenum");
            if (userId == null || "".equals(userId)) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("userId", Integer.valueOf(userId));
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            ArrayList<BxpProjectList> bxpProjectList = pCollectionService.ListPCollection(map);

            ReturnObj returnObj = new ReturnObj();
            if (bxpProjectList == null || bxpProjectList.size() == 0) {
                returnObj.setObj(null);
                returnObj.setFlag("没有收藏数据");
            } else {
                returnObj.setObj(bxpProjectList);
                returnObj.setFlag("获取收藏列表成功");
            }

            return returnObj;

        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 用户点赞
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/8/6 13:22
     */
    @PostMapping(value = "/like")
    public ReturnObj addLike(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        //用户Id
        String userId = null;
        //类型
        String type = null;
        //案件或资讯Id
        String pId = null;
        ReturnObj returnObj = new ReturnObj();
        try {
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            pId = request.getParameter("Id");
            if ((userId == null || "".equals(userId)) || (type == null || "".equals(type)) || (pId == null || "".equals(pId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Integer ii = likeService.getlike(Integer.valueOf(userId), Integer.valueOf(pId), Integer.valueOf(type));
            if (ii == null || ii.equals(0)) {
                likeService.save(Integer.valueOf(userId), Integer.valueOf(pId), Integer.valueOf(type));
                LikeUtil like = new LikeUtil();
                like.setType(true);
                returnObj.setObj(like);
                returnObj.setFlag("点赞成功");
            } else {
                likeService.remove(Integer.valueOf(userId), Integer.valueOf(pId), Integer.valueOf(type));
                LikeUtil like = new LikeUtil();
                like.setType(false);
                returnObj.setObj(like);
                returnObj.setFlag("取消点赞");
            }
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 取消点赞
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/8/6 13:23
     */
    @PostMapping(value = "/removelike")
    public ReturnObj removeLike(HttpServletRequest request, HttpServletResponse response) throws BxpException {

        //用户Id
        String userId = null;
        //类型
        String type = null;
        //案件或资讯Id
        String pId = null;
        try {
            userId = request.getParameter("userId");
            type = request.getParameter("type");
            pId = request.getParameter("pId");
            if ((userId == null || "".equals(userId)) || (type == null || "".equals(type)) || (pId == null || "".equals(pId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            likeService.remove(Integer.valueOf(userId), Integer.valueOf(pId), Integer.valueOf(type));

            ReturnObj returnObj = new ReturnObj();

            returnObj.setObj(null);
            returnObj.setFlag("取消点赞");


            return returnObj;


        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 案件详情
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/12 16:16
     */
    @PostMapping(value = "/projDetails")
    public ReturnObj projDetails(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String id = null;
        String type = null;
        String userId = null;
        try {
            id = request.getParameter("Id");
            type = request.getParameter("type");
            userId = request.getParameter("userId");
            if ((id == null || "".equals(id)) || (type == null || "".equals(type))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (userId == null || "".equals(userId)) {
                userId = "0";
            }
            BxpProject bxpProject = null;

            if (type.equals("0")) {
                if(Integer.valueOf(id)>9999999){
                    bxpProject = bxpProiService.getBxpProject(Integer.valueOf(id), Integer.valueOf(type), Integer.valueOf(userId));
                }else{
                    //老数据开始
                    HttpClient httpClient = new DefaultHttpClient();
                    String url =DataUrl+"/proj/projDetails?everypage=1&pagenum=1&Id="+id+"&userId="+userId;
                    HttpPost httpPost = new HttpPost(url);
                    HttpResponse resp = httpClient.execute(httpPost);
                    int statusCode = resp.getStatusLine().getStatusCode();
                    if(statusCode==200){
                        BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                        StringBuffer sb = new StringBuffer("");
                        String line = "";
                        String NL = System.getProperty("line.separator");
                        while ((line = in.readLine()) != null) {
                            sb.append(line + NL);
                        }
                        in.close();
                        String content = sb.toString();
                        JSONObject jsonObject = JSON.parseObject(content);
                        String returnFlag=(String) jsonObject.get("returnFlag");
                        if(returnFlag==null || returnFlag.equals("1")){
                            throw BxpExceptionEnum.REQUEST_FEILED.exception();
                        }else{
                            List<projreturnObj> projreturnObjs= JSON.parseArray(jsonObject.get("returnObj").toString(),projreturnObj.class);
                            bxpProject=new BxpProject();
                            for(projreturnObj projreturnobj : projreturnObjs){
                                bxpProject.setAddress(projreturnobj.getAddress());//发址
                                bxpProject.setAudiourl(projreturnobj.getAudiourl());//音频地址
                                bxpProject.setBrowsenumber(projreturnobj.getRevertnum());//浏览数
                                bxpProject.setCreationTime(new Date(projreturnobj.getCudate())); //上报地址
                                bxpProject.setFid(projreturnobj.getLon()+","+projreturnobj.getLat());//坐标
                                bxpProject.setId(projreturnobj.getId());//主键Id
                                bxpProject.setImgurl(projreturnobj.getImgurl());//图片地址
                                bxpProject.setIscollection(projreturnobj.getIsCollection());//是否收藏
                                bxpProject.setIslike(projreturnobj.getLikesum());//是否点赞
                                bxpProject.setLikenumber(projreturnobj.getLikesum());
                                bxpProject.setMome(projreturnobj.getMome());//内容
                                bxpProject.setNickname(projreturnobj.getNickname());//用户昵称
                                bxpProject.setPhoto(projreturnobj.getPhoto());//用户头像
                                bxpProject.setReplynumber(0);//回复数
                                bxpProject.setTitle(projreturnobj.getTitle());
                                bxpProject.setType(projreturnobj.getType());
                            }
                        }
                    } else{
                        throw BxpExceptionEnum.REQUEST_FEILED.exception();
                    }
                    //老数据结束
                }
            } else {
                if(Integer.valueOf(id)>9999999){
                bxpProject = infoService.searchInfoDetail(Integer.valueOf(id), Integer.valueOf(type), Integer.valueOf(userId));
            }else{
                    //老数据开始
                    HttpClient httpClient = new DefaultHttpClient();
                    String url =DataUrl+"/bxp/proj/projDetails?everypage=1&pagenum=1&Id="+id+"&userId="+userId;
                    HttpPost httpPost = new HttpPost(url);
                    HttpResponse resp = httpClient.execute(httpPost);
                    int statusCode = resp.getStatusLine().getStatusCode();
                    if(statusCode==200){
                        BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                        StringBuffer sb = new StringBuffer("");
                        String line = "";
                        String NL = System.getProperty("line.separator");
                        while ((line = in.readLine()) != null) {
                            sb.append(line + NL);
                        }
                        in.close();
                        String content = sb.toString();
                        JSONObject jsonObject = JSON.parseObject(content);
                        String returnFlag=(String) jsonObject.get("returnFlag");
                        if(returnFlag==null || returnFlag.equals("1")){
                            throw BxpExceptionEnum.REQUEST_FEILED.exception();
                        }else{
                            List<projreturnObj> projreturnObjs= JSON.parseArray(jsonObject.get("returnObj").toString(),projreturnObj.class);
                            bxpProject=new BxpProject();
                            for(projreturnObj projreturnobj : projreturnObjs){
                                bxpProject.setAddress(projreturnobj.getAddress());//发址
                                bxpProject.setAudiourl(projreturnobj.getAudiourl());//音频地址
                                bxpProject.setBrowsenumber(projreturnobj.getRevertnum());//浏览数
                                bxpProject.setCreationTime(new Date(projreturnobj.getCudate())); //上报地址
                                bxpProject.setFid(projreturnobj.getLon()+","+projreturnobj.getLat());//坐标
                                bxpProject.setId(projreturnobj.getId());//主键Id
                                bxpProject.setImgurl(projreturnobj.getImgurl());//图片地址
                                bxpProject.setIscollection(projreturnobj.getIsCollection());//是否收藏
                                bxpProject.setIslike(projreturnobj.getLikesum());//是否点赞
                                bxpProject.setLikenumber(projreturnobj.getLikesum());
                                bxpProject.setMome(projreturnobj.getMome());//内容
                                bxpProject.setNickname(projreturnobj.getNickname());//用户昵称
                                bxpProject.setPhoto(projreturnobj.getPhoto());//用户头像
                                bxpProject.setReplynumber(0);//回复数
                                bxpProject.setTitle(projreturnobj.getTitle());
                                bxpProject.setType(projreturnobj.getType());
                            }
                        }
                    } else{
                        throw BxpExceptionEnum.REQUEST_FEILED.exception();
                    }
                    //老数据结束
                }
            }
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(bxpProject);
            returnObj.setFlag("获取案件详情成功");
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 查看回复记录
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/13 9:14
     */
    @PostMapping(value = "/projreplyDetails")
    public ReturnObj projreplyDetails(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String id = null;
        String type = null;
        String uesrId = null;
        String Page = null;
        String PageSize = null;
        try {
            id = request.getParameter("id");
            if (id == null) {
                id = request.getParameter("Id");
            }
            type = request.getParameter("type");
            //Integer j=Integer.valueOf(type);
            uesrId = request.getParameter("uesrId");

            if ((id == null || "".equals(id)) || (type == null || "".equals(type))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            if (uesrId == null || "".equals(uesrId)) {
                uesrId = "0";
            }
            Page = request.getParameter("Page");
            PageSize = request.getParameter("PageSize");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", Integer.valueOf(id));
            map.put("type", Integer.valueOf(type));
            map.put("uesrId", Integer.valueOf(uesrId));
            map.put("Page1", Page);
            map.put("PageSize1", PageSize);
            Integer[] i = PageUtil.paging(Page, PageSize);
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            ReplyUtil Preplys = replyService.selectReplyByPId(map);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(Preplys);
            returnObj.setFlag("获取案件回复信息列表成功");
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping(value = "/newline")
    public ReturnObj newline(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        try {
            ArrayList<HashMap<String, Object>> map = bxpProiService.getsum();
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(map);
            returnObj.setFlag("获取信息成功");
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping(value = "/getarea")
    public ReturnObj getarea(HttpServletRequest request, HttpServletResponse response) throws BxpException {

        try {
            // ArrayList<HashMap<String, Object>> area = new ArrayList<HashMap<String, Object>>();
            ArrayList<HashMap<String, Object>> areas = sysAreaService.selectarea();
         /*   if (areas != null) {
                for (HashMap<String, Object> map : areas) {
                    String id = map.get("id").toString();
                    ArrayList<HashMap<String, Object>> ways = this.sysAreaService.getway(Integer.valueOf(id));
                    map.put("date", ways);
                    area.add(map);
                }
            }*/
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(areas);
            returnObj.setFlag("获取信息成功");
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping(value = "/addevaluation")
    public ReturnObj addevaluation(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        try {
            String satisfaction = request.getParameter("satisfaction");
            String condition = request.getParameter("condition");
            String area = request.getParameter("area");
            String way = request.getParameter("way");
            String userid = request.getParameter("userid");
            String opinion = request.getParameter("opinion");
            if ((satisfaction == null) || (satisfaction.equals("")) || (area == null) || (area.equals(""))
                    || (way == null) || (way.equals("")) || (userid == null) || (userid.equals(""))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("satisfaction", satisfaction);
            map.put("condition", condition);
            map.put("area", area);
            map.put("way", way);
            map.put("userid", userid);
            map.put("opinion", opinion);
            this.sysAreaService.addevaluation(map);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(null);
            returnObj.setFlag("满意度评价成功");
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    /**
     * 我的奖励列表
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/27 16:27
     */
    @PostMapping(value = "/myCashList")
    public ReturnObj myCashList(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        ReturnObj returnObj = new ReturnObj();
        String uId=null;
        try {
            uId = request.getParameter("uId");
            if ((uId == null || "".equals(uId))) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            String flag = request.getParameter("flag");
            if ((flag == null) || (flag.equals("-1"))) {
                flag = "";
            }
            String everypage = request.getParameter("everypage");
            if ((everypage == null) || (everypage.equals(""))) {
                everypage = "10";
            }
            String pagenum = request.getParameter("pagenum");
            if ((pagenum == null) || (pagenum.equals(""))) {
                pagenum = "1";
            }
            Integer[] i = PageUtil.paging(pagenum, everypage);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("flag", flag);
            map.put("uId", uId);
            map.put("everypage",1000);
            map.put("pagenum",0);
       /*   map.put("everypage", Integer.valueOf(everypage));
            map.put("pagenum", (Integer.valueOf(pagenum) - 1) * Integer.valueOf(everypage));*/
            ArrayList<MyProjectAward> MyProjectAward = ibxpProjAwardService.MyProjectAwardList(map);

            //老数据开始

            HttpClient httpClient = new DefaultHttpClient();
            String url =DataUrl+"/proj/myCashList?everypage=1000&pagenum=1&state="+flag+"&uId="+uId;

            HttpPost httpPost = new HttpPost(url);
            HttpResponse resp = httpClient.execute(httpPost);
            int statusCode = resp.getStatusLine().getStatusCode();
            if(statusCode==200){

                BufferedReader in = new BufferedReader(new InputStreamReader(resp.getEntity()
                        .getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                String content = sb.toString();
                JSONObject jsonObject = JSON.parseObject(content);
                String returnFlag=(String) jsonObject.get("returnFlag");
                if(returnFlag==null || returnFlag.equals("1")){
                    throw BxpExceptionEnum.REQUEST_FEILED.exception();
                }else{
                    List<MyProjectAwarddto> MyProjectAwarddtos= JSON.parseArray(jsonObject.get("returnObj").toString(),MyProjectAwarddto.class);
                    for(MyProjectAwarddto myProjectAwarddto: MyProjectAwarddtos){
                        MyProjectAward myProjectAward=new MyProjectAward();

                        myProjectAward.setCudate(new Date(myProjectAwarddto.getCudate()));
                        myProjectAward.setFlag(myProjectAwarddto.getState()-1);
                        myProjectAward.setId(myProjectAwarddto.getId());
                        myProjectAward.setMome(myProjectAwarddto.getMome());
                        myProjectAward.setMoney(new  BigDecimal(myProjectAwarddto.getMoney()));
                        myProjectAward.setpId(0);
                        myProjectAward.setScore(myProjectAwarddto.getScore());
                        myProjectAward.setTasktype(0);
                        myProjectAward.setUserId(myProjectAwarddto.getUserId());
                        MyProjectAward.add(myProjectAward);
                    }
                }
            } else{
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
            //老数据结束

            if(MyProjectAward==null || MyProjectAward.size()< i[0]){
                ArrayList<MyProjectAward> MyProjectAward1=new ArrayList<>();
                returnObj.setObj(MyProjectAward1);
                returnObj.setFlag("没有案件数据");
            }
            if( MyProjectAward.size()>i[0]  && ( MyProjectAward.size()> (i[0]+i[1]))){
                ArrayList<MyProjectAward> bxpProjectList1=new ArrayList<>();
                for(int j= i[0];j<(i[0]+i[1]);j++){
                    bxpProjectList1.add(MyProjectAward.get(j));
                }
                returnObj.setObj(bxpProjectList1);
                returnObj.setFlag("获取案件列表成功");
            }
            if( MyProjectAward.size()>i[0]  && ( MyProjectAward.size()< (i[0]+i[1]))){
                ArrayList<MyProjectAward> bxpProjectList1=new ArrayList<>();
                for(int j= i[0];j<MyProjectAward.size();j++){
                    bxpProjectList1.add(MyProjectAward.get(j));
                }
                returnObj.setObj(bxpProjectList1);
                returnObj.setFlag("获取案件列表成功");
            }
            return returnObj;
        } catch (BxpException e) {
            throw e;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

    @PostMapping(value = "/help")
    public ReturnObj help(HttpServletRequest request, HttpServletResponse response) throws BxpException {
        String pram = null;
        try {
            pram = request.getParameter("pram");
            HashMap<String, Object> map = iPramService.getPram(pram);
            ReturnObj returnObj = new ReturnObj();
            returnObj.setObj(map);
            returnObj.setFlag("获取数据成功");
            return returnObj;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

}



