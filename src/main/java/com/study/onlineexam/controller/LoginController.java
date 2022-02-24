package com.study.onlineexam.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.onlineexam.config.JwtTokenManager;
import com.study.onlineexam.constant.ShiroConstant;
import com.study.onlineexam.entity.User;
import com.study.onlineexam.service.UserService;
import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @PostMapping
    @ApiOperation("登录只需填入用户名和密码，返回token(过期时间为永久)")
    private ResponseVo login(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装当前用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            String jwtToken ;
            subject.login(token);
            String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            Long id = userService.getOne(queryWrapper).getId();
            Map<String,Object> claims = new HashMap<>();
            claims.put("id", JSONObject.toJSONString(id));
            jwtToken = jwtTokenManager.issuedToken("system", -1, sessionId, claims);
            return ResponseVo.result( ShiroConstant.LOGIN_SUCCESS_CODE,ShiroConstant.LOGIN_SUCCESS_MESSAGE,jwtToken);
        } catch (UnknownAccountException e) {
            token.clear();
            return ResponseVo.result(ShiroConstant.LOGIN_FAILURE_CODE,ShiroConstant.LOGIN_FAILURE_MESSAGE);
        } catch (IncorrectCredentialsException e) {
            token.clear();
            return ResponseVo.result(ShiroConstant.LOGIN_FAILURE_CODE, ShiroConstant.LOGIN_FAILURE_MESSAGE);
        }catch (ExcessiveAttemptsException ex) {
            token.clear();
            return ResponseVo.result(ShiroConstant.PWD_COUNT_CODE,ShiroConstant.PWD_COUNT_MESSAGE,ex.getMessage());
        }
    }



}
