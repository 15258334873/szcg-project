package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.MyBxpProject;
import tx.bxp.entity.*;
import tx.bxp.mapper.BrowseLikeMapper;
import tx.bxp.mapper.BxpProjectDetailMapper;
import tx.bxp.mapper.BxpProjectMapper;
import tx.bxp.mapper.FileMapper;
import tx.bxp.service.IbxpProiService;
import tx.bxp.util.FileUtil;
import tx.commons.util.FileUploadUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @program: szcg-project
 * @description: 案件管理
 * @author:fyz
 * @create: 2018-07-16 09:35
 **/
@Transactional
@Service
@CacheConfig(cacheNames = "bxpProi")
public class BxpProiServiceImpl implements IbxpProiService {

    @Autowired
    private BxpProjectMapper bxpProjectMapper;

    @Autowired
    private BxpProjectDetailMapper bxpProjectDetailMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private BrowseLikeMapper browseLikeMapper;

    //
    @Value("${bxp.imgurl}")
    private String imgurl;

    //老版数据调用地址
    @Value("${bxp.DataUrl}")
    private String DataUrl;

    @Override
    public void savebxpPro(String address, String lon, String lat, String moment, String userId, MultipartFile[] files, String seq) {
        //案件表增加
        tx.bxp.entity.BxpProject bxpProject = new tx.bxp.entity.BxpProject();
        bxpProject.setProjName("贵阳市城管【" + seq + "】");
        bxpProject.setProjSourceWay(5);
        bxpProject.setSourcesystem(1);
        bxpProject.setCreatorUserId(Integer.valueOf(userId));
        bxpProjectMapper.InsertBxpProject(bxpProject);
        Integer id = bxpProject.getProjCode();
        //案件细节表新增
        BxpProjectDetail bxpProjectDetail = new BxpProjectDetail();
        bxpProjectDetail.setProjCode(id);
        bxpProjectDetail.setProjName("贵阳市城管【" + seq + "】");
        bxpProjectDetail.setProjNum(seq);
        bxpProjectDetail.setDevicesign(moment);
        String description = moment.replaceAll("来自贵阳百姓拍安卓客户端", "").replaceAll("来自贵阳百姓拍iPhone客户端", "");
        bxpProjectDetail.setProjDesc(description);
        bxpProjectDetail.setFid(lon + "," + lat);
        bxpProjectDetail.setCreatorUserId(Integer.valueOf(userId));
        bxpProjectDetail.setAddress(address);
        bxpProjectDetailMapper.InsertBxpProjectDetail(bxpProjectDetail);
        //案件奖励表新增
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("userId", userId);
        map.put("pId", id);
        bxpProjectMapper.InsertBxpProjectaward(map);
        //案件举报表新增
        bxpProjectMapper.InsertBxpProjectpub(id);
        BrowseLike browseLike = new BrowseLike();
        browseLike.setPid(id);
        browseLike.setBrowsenumber(0);
        browseLike.setLikenumber(0);
        browseLike.setType(0);
        browseLike.setReplynumber(0);
        browseLikeMapper.InsertBrowseLike(browseLike);
        //图片上传
        String flag = "";
        List<String> fileList = null;
        try {
            fileList = FileUploadUtils.uploadFile(imgurl, "/bxp/proj", files);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileList != null) {

            for (int i=0;i<fileList.size();i++) {
                int j=i+1;
                String suffix = fileList.get(i).substring(fileList.get(i).indexOf(".") + 1);
                if (suffix.equals("jpg") || suffix.equals("png")) {
                    flag = "1";
                }
                if (suffix.equals("mp3")) {
                    flag = "3";
                }
                if (suffix.equals("mp4")) {
                    flag = "2";
                }
                File file = new File();
                file.setPost_id(id);
                file.setFile_path(fileList.get(i));//路径
                file.setFile_type(flag);
                file.setFile_status(0); // 文件类型（0：案件上报:1：案件回复:2：核查回复 3:资讯）
                file.setPost_status(0); // 帖子类型 （0：案件类，1：资讯类）
                file.setFile_num(j); // 编号
                fileMapper.insertFileInfo(file);
            }
        }
    }

    @Override
    @Cacheable(key = "'BxpProjectList'")
    public ArrayList<BxpProjectList> BxpProjectList() {
        ArrayList<BxpProjectList> bxpProjectList = bxpProjectMapper.SelectBxpProjectList();
        return bxpProjectList;
    }

    @Override
    public tx.bxp.dto.BxpProject getBxpProject(Integer id, Integer type, Integer uesrId) {

        BrowseLike browseLike = new BrowseLike();
        browseLike.setPid(id);
        browseLike.setBrowsenumber(1);
        browseLike.setType(type);

        browseLikeMapper.updateBrowseLike(browseLike);
        HashMap<String, Object> map = new HashMap<>();
        map.put("uesrId", uesrId);
        map.put("id", id);
        return bxpProjectMapper.SelectBxpProjectById(map);
    }

    @Override
    public ArrayList<MyBxpProject> MyBxpProjectList(HashMap<String, Object> map) {
        return bxpProjectMapper.SelectMyBxpProjectList(map);
    }


    @Override
    public ArrayList<HashMap<String, Object>> getsum() {
        return bxpProjectMapper.selectSUM();
    }

    @Override
    public Integer getuserIdCount(Integer userId) {
        return bxpProjectMapper.selectbyUseridCount(userId);
    }

    @Override
    public Userinfo getBxpProjectByID(Integer id) {
        return bxpProjectMapper.SelectBxpProjectById1(id);
    }
}