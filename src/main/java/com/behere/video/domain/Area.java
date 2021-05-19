package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * 地区
 * @author Fengj
 */
public class Area implements Param {

    private int id;

    private String name;

    private int pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}