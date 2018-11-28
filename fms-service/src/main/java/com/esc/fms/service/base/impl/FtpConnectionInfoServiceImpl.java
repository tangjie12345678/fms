package com.esc.fms.service.base.impl;

import com.esc.fms.dao.FtpConnectionInfoMapper;
import com.esc.fms.entity.FtpConnectionInfo;
import com.esc.fms.service.base.FtpConnectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tangjie on 2017/7/22.
 */
@Service("ftpConnectionInfoService")
public class FtpConnectionInfoServiceImpl implements FtpConnectionInfoService {

    @Autowired
    private FtpConnectionInfoMapper ftpConnectionInfoMapper;

    public FtpConnectionInfo getMasterFtpConnectionInfo() {
        return ftpConnectionInfoMapper.getMasterFtpConnectionInfo();
    }
}
