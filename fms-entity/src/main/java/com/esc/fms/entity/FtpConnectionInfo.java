package com.esc.fms.entity;

public class FtpConnectionInfo {
    private String hostIP;

    private Integer port;

    private String userName;

    private String password;

    private Boolean masterNode;

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP == null ? null : hostIP.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getMasterNode() {
        return masterNode;
    }

    public void setMasterNode(Boolean masterNode) {
        this.masterNode = masterNode;
    }
}