package com.study.onlineexam.controller;


import com.study.onlineexam.constant.CRUDConstant;
import com.study.onlineexam.entity.User;
import com.study.onlineexam.service.UserService;
import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping
    @ApiOperation("查询所有用户")
    public ResponseVo queryUserList(){
        List<User> userList = userService.queryUserList();
        if (userList != null){
            return ResponseVo.result(CRUDConstant.QUERY_SUCCESS_CODE, CRUDConstant.QUERY_SUCCESS_MESSAGE, userList);
        }else {
            return ResponseVo.result(CRUDConstant.QUERY_FAIL_CODE, CRUDConstant.QUERY_FAIL_MESSAGE);
        }
    }

    /**
     * 根据用户ID查询某个用户"
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据用户ID查询某个用户")
    public ResponseVo findUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        if(user != null){
            return ResponseVo.result(CRUDConstant.QUERY_SUCCESS_CODE, CRUDConstant.QUERY_SUCCESS_MESSAGE, user);
        } else{
            return ResponseVo.result(CRUDConstant.QUERY_FAIL_CODE, CRUDConstant.QUERY_FAIL_MESSAGE);
        }
    }

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑用户信息")
    public ResponseVo editUser(User user){
        if (user.getId() == null || userService.findUserById(user.getId()) == null){
            return ResponseVo.result(CRUDConstant.UPDATE_FAIL_CODE, CRUDConstant.UPDATE_FAIL_MESSAGE);
        }
        boolean i = userService.updateById(user);
        if (i ==true){
            return ResponseVo.result(CRUDConstant.UPDATE_SUCCESS_CODE, CRUDConstant.UPDATE_SUCCESS_MESSAGE);
        }else {
            return ResponseVo.result(CRUDConstant.UPDATE_FAIL_CODE, CRUDConstant.UPDATE_FAIL_MESSAGE);
        }
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation("根据ID删除用户")
    public ResponseVo deleteUserById(Long id){
        if (id == null){
            return ResponseVo.result(CRUDConstant.DELETE_FAIL_CODE, CRUDConstant.DELETE_FAIL_MESSAGE);
        }
        int i = userService.deleteUserById(id);
        if (i > 0){
            return ResponseVo.result(CRUDConstant.DELETE_SUCCESS_CODE, CRUDConstant.DELETE_SUCCESS_MESSAGE);
        }else{
            return ResponseVo.result(CRUDConstant.DELETE_FAIL_CODE, CRUDConstant.DELETE_FAIL_MESSAGE);
        }
    }
}

