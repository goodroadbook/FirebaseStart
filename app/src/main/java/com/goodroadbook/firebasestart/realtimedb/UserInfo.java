package com.goodroadbook.firebasestart.realtimedb;

public class UserInfo
{
    private String userpwd;
    private String username;
    private String emailaddr;

    public String getUserpwd()
    {
        return userpwd;
    }

    public void setUserpwd(String userpwd)
    {
        this.userpwd = userpwd;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmailaddr()
    {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr)
    {
        this.emailaddr = emailaddr;
    }
}