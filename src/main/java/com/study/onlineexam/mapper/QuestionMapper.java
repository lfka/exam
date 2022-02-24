package com.study.onlineexam.mapper;

import com.study.onlineexam.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-06
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<Question> queryQuestionList();
}
