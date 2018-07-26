package com.zn.notes.transcationtest.service.impl;

import com.zn.notes.transcationtest.entity.User1;
import com.zn.notes.transcationtest.mapper.User1Mapper;
import com.zn.notes.transcationtest.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhoun on 2018/7/26.
 **/
@Service
public class User1ServiceImpl implements User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user) {
        user1Mapper.insert(user);
    }

}
