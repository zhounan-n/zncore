package com.zn.notes.transcationtest.mapper;

import com.zn.notes.transcationtest.entity.User1;

public interface User1Mapper {

    int insert(User1 record);
    User1 selectByPrimaryKey(Integer id);

}
