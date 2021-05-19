package com.behere.socket;

import java.io.Serializable;

/**
 * @author: Behere
 */
public class Entity implements Serializable {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Entity [name=" + name + ", sex=" + sex + "]";
    }
}