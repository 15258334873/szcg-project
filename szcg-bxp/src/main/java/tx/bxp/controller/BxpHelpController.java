package tx.bxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tx.bxp.exception.BxpException;
import tx.bxp.exception.BxpExceptionEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-10-15 10:50
 **/
@Controller
@RequestMapping("help")
public class BxpHelpController {
    @GetMapping(value ="/*")
    public String help(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        String ServletPath = request.getServletPath();
        String name = ServletPath.split("/")[2];
        return name.substring(0, name.lastIndexOf("."));
    }
}

