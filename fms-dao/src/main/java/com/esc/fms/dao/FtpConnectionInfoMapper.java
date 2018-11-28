package com.esc.fms.dao;

import com.esc.fms.entity.FtpConnectionInfo;

public interface FtpConnectionInfoMapper {
    int insert(FtpConnectionInfo record);

    int insertSelective(FtpConnectionInfo record);

    FtpConnectionInfo getMasterFtpConnectionInfo();
}