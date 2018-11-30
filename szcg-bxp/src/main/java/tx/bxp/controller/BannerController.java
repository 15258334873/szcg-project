package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tx.bxp.entity.Banner;
import tx.bxp.service.IBannerService;

import java.util.List;

/**
 * @program: szcg-project
 * @description: 轮播图管理
 * @author:pck
 * @create: 2018-08-17 11:13
 **/
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private IBannerService BannerService;

    @PostMapping(value = "/list")
    public List<Banner> BannerList(){
        return  BannerService.BannerList();
    }
}
