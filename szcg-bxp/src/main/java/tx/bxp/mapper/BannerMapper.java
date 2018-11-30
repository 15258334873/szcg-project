package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.Banner;
import java.util.List;

/**
 * @program: szcg-project
 * @description: 轮播图管理
 * @author:pck
 * @create: 2018-08-17 11:18
 **/
@Mapper
public interface BannerMapper {

    public List<Banner> SelectBannerByList();

}
