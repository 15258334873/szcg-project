package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.BxpProject;
import tx.bxp.entity.BxpProjectDetail;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-12 10:24
 **/
@Mapper
public interface BxpProjectDetailMapper {

   public Integer InsertBxpProjectDetail(BxpProjectDetail bxpProjectDetail);

}
