package com.esc.fms.controller.right;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tangjie on 2016/11/15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("login")
    public ModelAndView gotoLoginPage()
    {
        return new ModelAndView("login");
    }

    @RequestMapping("/index")
    public ModelAndView gotoIndexPage() {
        return new ModelAndView("index");
    }
}
