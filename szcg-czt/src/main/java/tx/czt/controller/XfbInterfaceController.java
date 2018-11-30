package tx.czt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: szcg-project
 * @description:处置通功能
 * @author:pck
 * @create: 2018-09-13 09:29
 **/
@RestController
@RequestMapping("/xfbInterface")
public class XfbInterfaceController {

    @GetMapping("/xfbInterface.asmx")
    public void xfbInterface(){
        System.out.println("kjxf");
    }
}
