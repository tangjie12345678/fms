package com.esc.fms.dao;

import com.esc.fms.entity.SharedFile;

public interface SharedFileMapper {
    int deleteByPrimaryKey(Integer fileID);

    int insert(SharedFile record);

    int insertSelective(SharedFile record);

    SharedFile selectByPrimaryKey(Integer fileID);

    int updateByPrimaryKeySelective(SharedFile record);

    int updateByPrimaryKey(SharedFile record);
}