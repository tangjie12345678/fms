package com.esc.fms.service.base;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Created by tangjie on 2017/8/6.
 */
public interface FtpService {

    public FTPClient getFtpClient() throws IOException;

    public void closeFtpConnection(FTPClient ftpClient) throws IOException;
}
