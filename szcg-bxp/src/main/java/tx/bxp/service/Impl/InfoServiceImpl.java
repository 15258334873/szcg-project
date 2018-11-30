package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.entity.BrowseLike;
import tx.bxp.entity.File;
import tx.bxp.entity.Info;
import tx.bxp.mapper.BrowseLikeMapper;
import tx.bxp.mapper.InfoMapper;
import tx.bxp.mapper.FileMapper;
import tx.bxp.service.IInfoService;
import tx.bxp.util.FileUtil;
import tx.commons.util.FileUploadUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-06-28 14:00
 **/

@Service
@Transactional(rollbackFor =Exception.class)
@CacheConfig(cacheNames = "info")
public class InfoServiceImpl implements IInfoService{

    @Autowired
    private InfoMapper Info;

    @Autowired
    private FileMapper File;

    @Autowired
    private BrowseLikeMapper browseLikeMapper;

    @Value("${bxp.imgurl}")
    private String imgurl;

    /**
     * 新增资讯
     * @author chenmc
     * @date 2018/8/17 10:32
     * @param
     * @return
     */
    @Override
    public  void insertInfo(Info info,MultipartFile[] files){
        Info.insertInfo(info);
        int id=info.getId();
        BrowseLike browseLike = new BrowseLike();
        browseLike.setPid(id);
        browseLike.setBrowsenumber(0);
        browseLike.setLikenumber(0);
        browseLike.setType(info.getInfo_type());
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
                file.setFile_status(3); // 文件类型（0：案件上报:1：案件回复:2：核查回复 3:资讯）
                file.setPost_status(1); // 帖子类型 （0：案件类，1：资讯类）
                file.setFile_num(j); // 编号
                File.insertFileInfo(file);
            }
        }

    }

   /**
    * 查询资讯列表
    * @author chenmc
    * @date 2018/8/17 10:33
    * @param
    * @return
    */
    @Override
    @Cacheable(key = "'searchInfoList4'")
    public ArrayList<BxpProjectList> searchInfoList4(Integer type){
       return Info.searchInfoList(type);
    }
    @Override
    @Cacheable(key = "'searchInfoList5'")
    public ArrayList<BxpProjectList> searchInfoList5(Integer type){
        return Info.searchInfoList(type);
    }

    @Override
    @Cacheable(key = "'searchInfoList6'")
    public ArrayList<BxpProjectList> searchInfoList6(Integer type){
        return Info.searchInfoList(type);
    }

    @Override
    @Cacheable(key = "'searchInfoList7'")
    public ArrayList<BxpProjectList> searchInfoList7(Integer type){
        return Info.searchInfoList(type);
    }

    /**
     * 查询资讯详情
     * @author chenmc
     * @date 2018/8/17 10:34
     * @param
     * @return
     */
    @Override
    public BxpProject searchInfoDetail(Integer id,Integer type,Integer uesrId){
        BrowseLike browseLike=new BrowseLike();
        browseLike.setPid(id);
        browseLike.setBrowsenumber(1);
        browseLike.setType(type);
        browseLikeMapper.updateBrowseLike(browseLike);
        HashMap<String, Object> map=new HashMap<>();
        map.put("userid",uesrId);
        map.put("id",id);
        map.put("type",type);
        return Info.searchInfoDetail(map);
    }
}
