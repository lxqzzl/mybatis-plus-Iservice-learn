package com.generator.mppre.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.generator.mppre.entity.TestDO;
import com.generator.mppre.dao.TestMapper;
import com.generator.mppre.service.TestService;

/**
 * @Description TestServiceImpl服务实现类
 * @author lxq
 * @since 2020-09-20 18:19:08
 */

@Service
@Component("TestService")
public class TestServiceImpl extends ServiceImpl<TestMapper, TestDO> implements TestService {

}