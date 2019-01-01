package com.tx.app.entity;

import java.io.Serializable;

/**
 *  *  @ClassName User
 *  *  @Description 用户实体类
 *  *  @Author Hardy
 *  *  @Date 2018年12月06日 11:47
 *  *  @Version 1.0.0
 *  
 **/
public class User implements Serializable {
    private Integer uid;//用户ID
    private String password;//登录密码
    private String username;//登录账号
    private String realname;//真实姓名
    private String loginIp;//登录IP
    private String regIp;//注册IP
    private String agUsername;//AG游戏登录账号
    private String agPassword;//AG游戏登录密码
    private String hgUsername;//HG游戏登录账号
    private String mg_username;//HG游戏登录密码
    private String email;//邮箱地址
    private String ipLevel;//ip等级
    private String mobile;//移动电话
    private String cagent;//平台编码
    private String isDaili;//是否为代理账号
    private String isDelete;//是否已删除
    private String qkKwd;//取款密码
    private String regDate;//注册时间
    private String loginTime;//登录时间
    private String wallet;//钱包余额
    private String topUid;//上级ID
    private String isStop;//是否停用
    private String isMobile;//是否移动端
    private String rmk;//备注
    private String typeId;//平台分层ID
    private String juniorUid;//推荐人ID
    private String regurl;//注册url
    private String loginmobile;//登录移动电话

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getAgUsername() {
        return agUsername;
    }

    public void setAgUsername(String agUsername) {
        this.agUsername = agUsername;
    }

    public String getAgPassword() {
        return agPassword;
    }

    public void setAgPassword(String agPassword) {
        this.agPassword = agPassword;
    }

    public String getHgUsername() {
        return hgUsername;
    }

    public void setHgUsername(String hgUsername) {
        this.hgUsername = hgUsername;
    }

    public String getMg_username() {
        return mg_username;
    }

    public void setMg_username(String mg_username) {
        this.mg_username = mg_username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpLevel() {
        return ipLevel;
    }

    public void setIpLevel(String ipLevel) {
        this.ipLevel = ipLevel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCagent() {
        return cagent;
    }

    public void setCagent(String cagent) {
        this.cagent = cagent;
    }

    public String getIsDaili() {
        return isDaili;
    }

    public void setIsDaili(String isDaili) {
        this.isDaili = isDaili;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getQkKwd() {
        return qkKwd;
    }

    public void setQkKwd(String qkKwd) {
        this.qkKwd = qkKwd;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getTopUid() {
        return topUid;
    }

    public void setTopUid(String topUid) {
        this.topUid = topUid;
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getJuniorUid() {
        return juniorUid;
    }

    public void setJuniorUid(String juniorUid) {
        this.juniorUid = juniorUid;
    }

    public String getRegurl() {
        return regurl;
    }

    public void setRegurl(String regurl) {
        this.regurl = regurl;
    }

    public String getLoginmobile() {
        return loginmobile;
    }

    public void setLoginmobile(String loginmobile) {
        this.loginmobile = loginmobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", regIp='" + regIp + '\'' +
                ", agUsername='" + agUsername + '\'' +
                ", agPassword='" + agPassword + '\'' +
                ", hgUsername='" + hgUsername + '\'' +
                ", mg_username='" + mg_username + '\'' +
                ", email='" + email + '\'' +
                ", ipLevel='" + ipLevel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", cagent='" + cagent + '\'' +
                ", isDaili='" + isDaili + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", qkKwd='" + qkKwd + '\'' +
                ", regDate='" + regDate + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", wallet='" + wallet + '\'' +
                ", topUid='" + topUid + '\'' +
                ", isStop='" + isStop + '\'' +
                ", isMobile='" + isMobile + '\'' +
                ", rmk='" + rmk + '\'' +
                ", typeId='" + typeId + '\'' +
                ", juniorUid='" + juniorUid + '\'' +
                ", regurl='" + regurl + '\'' +
                ", loginmobile='" + loginmobile + '\'' +
                '}';
    }
}
