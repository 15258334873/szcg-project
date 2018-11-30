package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.AppVersion;

/**
 * @program: szcg-project
 * @description: 版本管理
 * @author:pck
 * @create: 2018-08-20 09:15
 **/
@Mapper
public interface VersionMapper {

    public AppVersion SelectVserion();

}
