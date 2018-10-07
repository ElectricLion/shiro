package controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import realm.MyRealm;


/**
 * @author:tanghui
 * @since 1.0
 */
@Controller
@RequestMapping("/user")
public class PageController {
    private static Logger logger = Logger.getLogger(PageController.class);
    @Autowired
    MyRealm realm;

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    /**
     * 未登录请求需要授权的网页
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 未授权提示页面
     *
     * @return
     */
    @RequestMapping("/unAuth")
    public String unAuth() {
        return "unAuth";
    }

    /**
     * 登录页面，认证逻辑处理
     *
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login( String name, String password, Model model) {
        logger.info("name---------" + name);
        //1.先获得一个Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2.将用户名和密码封装到UsernamepasswordToken 这个对象中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        //3.使用subject对象调用login()登录认证方法。进行认证
        try {
            subject.login(usernamePasswordToken);
            //4.将认证后的用户名字取出
            name = (String) subject.getPrincipal();
            //5.可以使用单点登录存放在redis和Cookie 中
          /*  UUID uuid = UUID.randomUUID();
              Cookie cookie= new Cookie(uuid+name,name);
           */
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名不正确");
            //或者抛出异常
            throw new UnknownAccountException("用户名不正确");
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException("用户密码不正确");
        }
        //6.验证成功，跳转到首页

        model.addAttribute("name", name);
        return "index";
    }


    /**
     * 局部处理异常，只出来当前Controller 层的。
     *
     * @param
     * @param
     * @return
     *//*
    @ExceptionHandler(value = Exception.class)
    public String handlerException(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("e", e);
        return "error";
    }*/
}
