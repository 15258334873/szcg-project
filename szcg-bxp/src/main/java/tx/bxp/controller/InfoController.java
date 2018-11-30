package tx.bxp.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.Infodetail;
import tx.bxp.entity.Info;
import tx.bxp.dto.Infolist;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.IInfoService;
import tx.bxp.util.FileUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 资讯管理
 * @author FYZ
 * @date 2018-6-30
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private IInfoService iinfoService;

    /**
     * 新增资讯
     * @author FYZ
     * @date 2018/7/11 14:23
     * @param
     * @Return
     */
   /* @PostMapping("insertInfo")
    public String insertInfo(Info info, HttpServletRequest request, @RequestParam("files") MultipartFile[] files)throws BxpException {
        try {
            if(info.getUserid()==null||info.getTitle()==null||info.getContent()==null){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            iinfoService.insertInfo(info,files);
            return "新增资讯成功";
        }catch (BxpException e){
            throw e;
        }catch (Exception ex){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }*/

    /**
     * 查询资讯列表
     * @author FYZ
     * @date 2018/7/2 14:23
     * @param
     * @return
     */
    /*@PostMapping("/searchInfoList")*/
    /*public List<Infolist> searchInfoList(@Param("infotype") Integer infotype,@Param("pagenum") Integer pagenum,@Param("pagesize") Integer pagesize)throws BxpException{
        try {
            if(infotype==null){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            List<Infolist> list=iinfoService.searchInfoList();
            return list;
        }catch (BxpException EX){
            throw EX;
        }catch (Exception E){
            System.out.println(infotype);
            throw  BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }*/

    /**
     * 查询资讯详情
     * @author FYZ
     * @date 2018/7/24 14:23
     * @param
     * @return
     */
   /* @PostMapping("/searchInfoDetail")
    public Infodetail searchInfoDetail(@Param("id") Integer id,@Param("userid") Integer userid)throws BxpException{
        try {
            if(id==null){
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("id",id);
            map.put("userid", userid);
            Infodetail infolist= iinfoService.searchInfoDetail(map);
            return infolist;
            }catch (BxpException E){
            throw E;
        }catch (Exception EX){
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }

*/

}
