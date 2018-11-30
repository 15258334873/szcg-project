package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.File;

/**
 * @program: tx.framework
 * @description:
 * @author:fyz
 * @create: 2018-07-09 10:27
 **/
@Mapper
   public interface FileMapper {
    /**
     * 新增附件
     * @Title: insertInfo
     * @Description:
     * @param @param
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    void insertFileInfo(File file);
    

}


