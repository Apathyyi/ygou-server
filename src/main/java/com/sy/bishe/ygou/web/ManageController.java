package com.sy.bishe.ygou.web;


import com.sy.bishe.ygou.bean.AdminBean;
import com.sy.bishe.ygou.bean.GoodsInfoBean;
import com.sy.bishe.ygou.service.GoodsInfoService;
import com.sy.bishe.ygou.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller


public class ManageController {

@Autowired
ManageService manageService;

@Autowired
    GoodsInfoService goodsInfoService;

private String name;

    @RequestMapping(value = "admin/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        System.out.println(userName+passWord);
        AdminBean adminBean = manageService.getAdminByName(userName);
       System.out.println("用户名字和密码" +adminBean.getAdm_name()+adminBean.getAdm_pass());
        if (adminBean == null){
            System.out.println("为控");
            modelAndView.setViewName("log");
        }else {
            if (passWord.equals(adminBean.getAdm_pass())){
                System.out.println("登陆成功");
                modelAndView.setViewName("managePage");
                List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByAdmin();
                modelAndView.addObject("list",goodsInfoList);
                name = userName;
                session.setAttribute("adm_name",userName);
            }else {
                modelAndView.setViewName("log");
                System.out.println("登陆失败");
            }
        }
        return modelAndView;
    }

    @GetMapping(value = "/index")
    public String index(){
        return "log";
    }


    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @RequestMapping(value = "admin/agree",method = RequestMethod.GET)
    public ModelAndView agree(HttpServletRequest request,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String id = request.getParameter("id");
        System.out.println(id);


        int result = goodsInfoService.updateGoodsToAgree(id);
        if (result!=0){
            //修改成功
            //重新读取
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByAdmin();
            modelAndView.addObject("list",goodsInfoList);
            session.setAttribute("adm_name","admin");
        }
        modelAndView.setViewName("managePage");
        System.out.println("进入Controller");
        return modelAndView;
    }

    @RequestMapping(value = "admin/refuse",method = RequestMethod.GET)
    public ModelAndView refuse(HttpServletRequest request,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String id = request.getParameter("id");
        System.out.println(id);
        int result = goodsInfoService.updateGoodsToRefuse(id);
        if (result!=0){
            //修改成功
            //重新读取
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByAdmin();
            modelAndView.addObject("list",goodsInfoList);
            session.setAttribute("adm_name","admin");
        }
        modelAndView.setViewName("managePage");
        System.out.println("进入Controller");
        return modelAndView;
    }

}
