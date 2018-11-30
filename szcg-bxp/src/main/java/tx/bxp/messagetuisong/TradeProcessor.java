package tx.bxp.messagetuisong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tx.bxp.dto.MyPTask;
import tx.bxp.entity.PTask;
import tx.bxp.entity.Reply;
import tx.bxp.entity.Userinfo;
import tx.bxp.service.IReplyService;
import tx.bxp.service.ITaskService;
import tx.bxp.service.IbxpProiService;
import tx.bxp.util.jdpush;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息中间件功能实现
 *
 * @param
 * @author chenmc
 * @date 2018/9/14 8:59
 * @return
 */
@Component
public class TradeProcessor {

    @Autowired
    private IbxpProiService proiService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IReplyService replyService;


    /**
     * 核查
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/13 15:14
     */
    @RabbitListener(queues = "check")
    @RabbitHandler
    public void Check(String content) throws Exception {
        System.out.println("check");
        System.out.println(content);
        JSONObject jsonArray = JSON.parseObject(content);
        String projectId = (String) jsonArray.get("projectId");
        String operation = (String) jsonArray.get("operation");

            //核查不通过
            if (operation.equals("5")) {
                PTask task = taskService.GetPTask2(Integer.valueOf(projectId));
                if (task != null) {
                taskService.editpastPTaskstate2(task.getId());
                }
            }
            if (operation.equals("3")) {
                PTask task = taskService.GetPTask2(Integer.valueOf(projectId));
                if (task != null) {
                    taskService.editpastPTaskstate2(task.getId());
                }
            }
        try {
            Userinfo userinfo = proiService.getBxpProjectByID(Integer.valueOf(projectId));
            if (userinfo == null) {
            } else {
                if (userinfo.getFlag() == null) {
                } else if (userinfo.getFlag().equals("0")) {
                    //安卓
                    if (userinfo.getChannelid() == null) {
                    } else {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("type", 0);
                        map.put("id", Long.valueOf(projectId));
                        jdpush.jpushAndroidtag(map, operation, userinfo.getChannelid());
                    }
                } else if (userinfo.getFlag().equals("1")) {
                    //IOS

                }
            }

        } catch (Exception ex) {

        }
    }

    /**
     * 结案
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/13 15:14
     */
    @RabbitListener(queues = "over")
    @RabbitHandler
    public void Over(String content) {
        System.out.println("over");
        JSONObject jsonArray = JSON.parseObject(content);
        String projectId = (String) jsonArray.get("projectId");
        String operation = (String) jsonArray.get("operation");
        Userinfo userinfo = proiService.getBxpProjectByID(Integer.valueOf(projectId));
        if (userinfo == null) {
        } else {
            if (userinfo.getFlag() == null) {
            } else if (userinfo.getFlag().equals("0")) {
                //安卓
                if (userinfo.getChannelid() == null) {
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("type", 0);
                    map.put("id", Long.valueOf(projectId));
                    jdpush.jpushAndroidtag(map, operation, userinfo.getChannelid());
                }
            } else if (userinfo.getFlag().equals("1")) {
                //IOS

            }
        }
    }

    /**
     * 立案
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/13 15:14
     */
    @RabbitListener(queues = "register")
    @RabbitHandler
    public void Register(String content) {
        System.out.println("register");
        JSONObject jsonArray = JSON.parseObject(content);
        String projectId = (String) jsonArray.get("projectId");
        String operation = (String) jsonArray.get("operation");
        if (operation.equals("4")) {
            String opinion = (String) jsonArray.get("opinion");
            Reply reply = new Reply();
            reply.setUserid(88);
            reply.setContent(opinion);
            reply.setProjid(Integer.valueOf(projectId));
            reply.setType(0);
            reply.setFid("0,0");
            reply.setAddress("贵阳市城管局");
            replyService.insertReply(reply);

        }
        Userinfo userinfo = proiService.getBxpProjectByID(Integer.valueOf(projectId));
        if (userinfo == null) {
        } else {
            if (userinfo.getFlag() == null) {
            } else if (userinfo.getFlag().equals("0")) {
                //安卓
                if (userinfo.getChannelid() == null) {
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("type", 0);
                    map.put("id", Long.valueOf(projectId));
                    jdpush.jpushAndroidtag(map, operation, userinfo.getChannelid());
                }
            } else if (userinfo.getFlag().equals("1")) {
                //IOS

            }
        }
    }

    /**
     * 发送核查任务(群推)
     *
     * @param
     * @return
     * @author chenmc
     * @date 2018/9/13 15:14
     */
    @RabbitListener(queues = "sendTask")
    @RabbitHandler
    public void SendTask(String content) {

        System.out.println("sendTask");
          System.out.println(content);
        JSONObject jsonArray = JSON.parseObject(content);
        String projectId = (String) jsonArray.get("projectId");
        String operation = (String) jsonArray.get("operation");
        MyPTask myPTask = taskService.MyPTask1(Integer.valueOf(projectId));
        if(myPTask!=null){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("awid", myPTask.getAwid());
        map.put("projcode", Long.valueOf(projectId));
        map.put("mome", myPTask.getMome());
        map.put("gatherdate", myPTask.getGatherdate());
        map.put("worktime", myPTask.getWorktime());
        map.put("address", myPTask.getAddress());
        map.put("money", myPTask.getMoney());
        map.put("score", myPTask.getScore());
        map.put("fid", myPTask.getFid());
        map.put("fstate", myPTask.getFstate());
        map.put("ismake", myPTask.getIsmake());
        map.put("prjimg", myPTask.getPrjimg());
        map.put("videour", myPTask.getVideour());
        map.put("manageurl", myPTask.getManageurl());
        map.put("managevide", myPTask.getManagevide());
        map.put("type", 0);
        jdpush.jpushAndroid_IOS(map, operation);
        System.out.println(content);
        }
    }
}
