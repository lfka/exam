package com.study.onlineexam.service;

import com.study.onlineexam.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-05
 */
public interface UserService extends IService<User> {

    List<User> queryUserList();

    User findUserById(Long id);

    int deleteUserById(Long id);

    User selectByUsername(String username);
}
