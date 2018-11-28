package com.esc.fms.service.base.impl;

import com.esc.fms.entity.FtpConnectionInfo;
import com.esc.fms.service.base.FtpConnectionInfoService;
import com.esc.fms.service.base.FtpService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esc.fms.common.constant.Constants;
import java.io.IOException;

/**
 * Created by tangjie on 2017/8/6.
 */
@Service("ftpService")
public class FtpServiceImpl implements FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);

    private static String LOCAL_CHARSET = "GBK";

    @Autowired
    private FtpConnectionInfoService ftpConnectionInfoService;

    public FTPClient getFtpClient() throws IOException{
        FTPClient ftpClient = new FTPClient();
        FtpConnectionInfo fciObj = ftpConnectionInfoService.getMasterFtpConnectionInfo();

        if(logger.isDebugEnabled()){
            logger.debug("The IP of host is [{}],port is [{}],UserName is [{}], Password is [{}]",
                    fciObj.getHostIP(),fciObj.getPort(), fciObj.getUserName(), fciObj.getPassword());
        }

        try {
            ftpClient.connect(fciObj.getHostIP(), fciObj.getPort());
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if (ftpClient.login(fciObj.getUserName(), fciObj.getPassword())) {
                    if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                        LOCAL_CHARSET = "UTF-8";
                    }
                    ftpClient.setControlEncoding(LOCAL_CHARSET);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                }else{
                    StringBuilder msg = new StringBuilder();
                    msg.append("登录FTP服务器失败，用户名[").append(fciObj.getUserName()).append("],请检查用户名和密码！");
                    throw new IOException(msg.toString());
                }
            }else{
                StringBuilder msg = new StringBuilder();
                msg.append("连接FTP服务器失败，主机[").append(fciObj.getHostIP()).append("],端口[").append(fciObj.getPort()).append("]，请检查FTP服务器IP及端口！");
                throw new IOException(msg.toString());
            }
        }catch (IOException e){
            logger.error(e.getMessage());
            throw e;
        }

        return ftpClient;
    }

    public void closeFtpConnection(FTPClient ftpClient) throws IOException{
        if(ftpClient.isConnected()){
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error("关闭FTP连接失败:" + e.getMessage());
                throw e;
            }
        }
    }
}
