package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tx.bxp.entity.AppVersion;
import tx.bxp.entity.Userinfo;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IUserinfoService;
import tx.bxp.service.IVersionService;

/**
 * @program: szcg-project
 * @description: 版本管理
 * @author:pck
 * @create: 2018-08-17 15:10
 **/
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private  IVersionService VersionService;

    @Autowired
    private IUserinfoService UserinfoService;

    @PostMapping("/get")
    public AppVersion getversion(@Param("userid") Integer userid,@Param("version") String  version)  throws BxpException {
            try{
                if(userid!=null){
                    Userinfo userinfo=new Userinfo();
                    userinfo.setId(userid);
                    userinfo.setVersion(version);
                    UserinfoService.updateUser(userinfo);
                }

            }catch (Exception ex){
            }

            try {
                if (version== null || version.length()==0) {
                    throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
                }

                AppVersion appVersion= VersionService.getAppVersion();

                String Versioncode=appVersion.getVersioncode();
               if(Versioncode.equals(version)){
                   return new AppVersion();
               }else{
                   return appVersion;
               }
            } catch (BxpException e) {
                throw e;
            } catch (Exception ex) {
                throw BxpExceptionEnum.REQUEST_FEILED.exception();
            }
    }
}
