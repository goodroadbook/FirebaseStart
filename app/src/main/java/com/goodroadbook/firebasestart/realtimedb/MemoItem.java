package com.goodroadbook.firebasestart.realtimedb;

public class MemoItem
{
    private String user;
    private String memotitle;
    private String memocontents;

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getMemotitle()
    {
        return memotitle;
    }

    public void setMemotitle(String memotitle)
    {
        this.memotitle = memotitle;
    }

    public String getMemocontents()
    {
        return memocontents;
    }

    public void setMemocontents(String memocontents)
    {
        this.memocontents = memocontents;
    }
}

