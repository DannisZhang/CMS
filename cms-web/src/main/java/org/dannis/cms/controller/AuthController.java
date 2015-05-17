package org.dannis.cms.controller;

import org.dannis.cms.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户控制器
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-16 20:12
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @RequestMapping(value = "/login.ajax",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(String username,String password,HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        BaseResult result = new BaseResult();
        request.getSession().setAttribute("username", username);
        System.out.println("Username: " + username + ", Password: " + password);
        result.setSuccess(true);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        result.setMessage(basePath + "/workbench.html");
        response.setContentType("text/plain; charset=utf-8");
        return result;
    }
}
