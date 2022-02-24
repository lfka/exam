package com.study.onlineexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.onlineexam.entity.Subject;
import com.study.onlineexam.mapper.SubjectMapper;
import com.study.onlineexam.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public Subject findSubjectById(Long id) {
        return subjectMapper.selectById(id);
    }

    /**
     * 查询课程
     * @return
     */
    @Override
    public List<Subject> queryList() {
        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNotNull(Subject::getName);
        queryWrapper.eq(Subject::getState,1);
        return subjectMapper.selectList(queryWrapper);
    }



}
