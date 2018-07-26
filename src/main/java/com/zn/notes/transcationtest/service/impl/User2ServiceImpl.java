package com.zn.notes.transcationtest.service.impl;

import com.zn.notes.transcationtest.entity.User2;
import com.zn.notes.transcationtest.mapper.User2Mapper;
import com.zn.notes.transcationtest.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhoun on 2018/7/26.
 **/
@Service
public class User2ServiceImpl implements User2Service{

    @Autowired
    private User2Mapper user2Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 user){
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user){
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

}
