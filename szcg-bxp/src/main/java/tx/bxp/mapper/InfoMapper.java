package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.BxpProject;
import tx.bxp.dto.BxpProjectList;
import tx.bxp.dto.Infodetail;
import tx.bxp.dto.Infolist;
import tx.bxp.entity.Userinfo;
import tx.bxp.entity.Usermanage;
import tx.bxp.entity.Info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-09 10:27
 **/
@Mapper
public interface InfoMapper {

    /**
     * 新增资讯
     * @Title: insertInfo
     * @Description:
     * @param @param
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    void insertInfo(Info info);

    /**
     * 查询资讯列表
     * @Title: searchInfoList
     * @Description:
     * @param @param
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    ArrayList<BxpProjectList> searchInfoList(Integer type);

    /**
     * 查询资讯详情
     * @Title: deleteInfo
     * @Description:
     * @param @param id
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    BxpProject searchInfoDetail(HashMap<String,Object> map);

}


