package tx.bxp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tx.bxp.entity.Banner;
import tx.bxp.mapper.BannerMapper;
import tx.bxp.service.IBannerService;

import java.util.List;

/**
 * @program: szcg-project
 * @description: 轮播图管理
 * @author:pck
 * @create: 2018-08-17 11:18
 **/

@Transactional
@Service
@CacheConfig(cacheNames = "banner")
public class IBannerServiceImpl implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @Cacheable(key = "'BannerList'")
    public List<Banner> BannerList() {
        return bannerMapper.SelectBannerByList();
    }
}
