package tx.bxp.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.Preply;
import tx.bxp.dto.projreturnObj;
import tx.bxp.entity.BrowseLike;
import tx.bxp.entity.BxpProject;
import tx.bxp.entity.Reply;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.mapper.BrowseLikeMapper;
import tx.bxp.mapper.ReplyMapper;
import tx.bxp.service.IReplyService;
import tx.bxp.util.FileUtil;
import tx.bxp.util.ReplyUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: szcg-project
 * @description:
 * @author:fyz
 * @create: 2018-07-17 09:35
 **/

@Transactional
@Service
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private BrowseLikeMapper browseLikeMapper;

    //老版数据调用地址
    @Value("${bxp.DataUrl}")
    private String DataUrl;

    @Override
    public  void insertReply( Reply reply){
        replyMapper.insertReply(reply);
        BrowseLike browseLike=new BrowseLike();
        browseLike.setPid(reply.getProjid());
        browseLike.setBrowsenumber(1);
        browseLike.setReplynumber(1);
        browseLike.setType(reply.getType());
        browseLikeMapper.updateBrowseLike(browseLike);
        }

    @Override
    public ReplyUtil selectReplyByPId( HashMap<String, Object> map ) {

        BrowseLike browseLike=new BrowseLike();
        browseLike.setPid(Integer.valueOf( map.get("id").toString()));
        browseLike.setType( Integer.valueOf( map.get("type").toString()));
        Integer i=browseLikeMapper.selectBrowseLike(browseLike);
        ArrayList<Preply>  preplies= null;
        if(Integer.valueOf(map.get("id").toString())>9999999){
            preplies= replyMapper.selectReplyByPId(map);
        }else{
            try {
                //老数据开始
                HttpClient httpClient = new DefaultHttpClient();
                String url = DataUrl+"/bxp/proj/projDetails?everypage=" + map.get("PageSize1") + "&pagenum=" + map.get("Page1") + "&Id=" + map.get("id") + "&userId=" + map.get("uesrId");
                HttpPost httpPost = new HttpPost(url);
                HttpResponse resp = httpClient.execute(httpPost);
                int statusCode = resp.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    preplies=new ArrayList<>();
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
                    String returnFlag = (String) jsonObject.get("returnFlag");
                    List<projreturnObj> projreturnObjs = JSON.parseArray(jsonObject.get("returnObj").toString(), projreturnObj.class);
                    for (projreturnObj projreturnobj : projreturnObjs) {
                        Preply preply=new Preply();
                        preply.setType(projreturnobj.getType());
                        preply.setPid(projreturnobj.getParentId());
                        preply.setAddress(projreturnobj.getAddress());
                        preply.setContent(projreturnobj.getMome());
                        preply.setCudate(new Date(projreturnobj.getCudate()));
                        preply.setId(projreturnobj.getId());
                        preply.setIsPraise(0);
                        preply.setLikesum(projreturnobj.getLikesum());
                        preply.setNickname(projreturnobj.getNickname());
                        preply.setPhoto(projreturnobj.getPhoto());
                        preply.setUid(projreturnobj.getUserId());
                        preplies.add(preply);
                    }
                }
                //老数据结束

            }catch (Exception ex){
            }
        }
        ReplyUtil replyUtil=new ReplyUtil();
        replyUtil.setPreplies(preplies);
        replyUtil.setReplynumber(i);
        return replyUtil;
    }
}
