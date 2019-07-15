package com.sean.org.entity;

import java.io.Serializable;


public class Dept implements Serializable {

    private Long deptno;
    private String name;
    private String db_source;

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDb_source() {
        return db_source;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", name='" + name + '\'' +
                ", db_source='" + db_source + '\'' +
                '}';
    }
}
