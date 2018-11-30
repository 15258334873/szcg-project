package tx.bxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.dto.MyPTask;
import tx.bxp.entity.PTask;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;
import tx.bxp.service.ITaskService;
import tx.bxp.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @program: szcg-project
 * @description: 任务功能模块
 * @author:pck
 * @create: 2018-09-11 13:24
 **/

@RestController
@RequestMapping("/task")
public class PTaskController {

    @Autowired
    private ITaskService iTaskService;

    @PostMapping(value = "/sendProList")
    public List<MyPTask> sendProList(HttpServletRequest request) throws BxpException {
        String Page = null;
        String PageSize = null;
        try {
            Page = request.getParameter("Page");
            PageSize = request.getParameter("PageSize");
            if (Page == null || "".equals(Page)) {
                Page = "1";
            }
            if (PageSize == null || "".equals(PageSize)) {
                PageSize = "10";
            }

            Integer[] i = PageUtil.paging(Page, PageSize);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Page", i[0]);
            map.put("PageSize", i[1]);
            List<MyPTask> list = iTaskService.ListPTask(map);
            return list;
        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


    @PostMapping(value = "/getPro")
    public String  getPro(HttpServletRequest request) throws BxpException {
        String id = request.getParameter("Id");
        String uid = request.getParameter("userId");

        try {

            if (id == null || uid==null ) {
                throw BxpExceptionEnum.INCOMPLETE_INFORMATION.exception();
            }
            Integer.valueOf(uid);
            Integer.valueOf(id);
            PTask task=iTaskService.GetPTask(Integer.valueOf(id));
            if(task==null){
                throw BxpExceptionEnum.LOSE.exception();
            }
            if(task.getUserid()!=0){
                throw BxpExceptionEnum.LOOTED.exception();
            }

            task.setUserid( Integer.valueOf(uid));
            iTaskService.EditTask(task);


            return "抢单成功";

        } catch (Exception ex) {
            throw BxpExceptionEnum.REQUEST_FEILED.exception();
        }
    }


}
