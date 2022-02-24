package com.study.onlineexam.service;

import com.study.onlineexam.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-06
 */
public interface QuestionService extends IService<Question> {

    List<Question> queryQuestionList();
}
