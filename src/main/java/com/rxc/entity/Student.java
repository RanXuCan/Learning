package com.rxc.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @Description:
 * @author: RanXuCan
 * @date: 2020年08月25日 10:36
 */

@Data
public class Student {
    String name;
    String id;

    public enum Light {
        RED("红色", 1),
        GREEN("绿色", 2),
        YELLOW("黄色", 3);
        private String name;
        private int index;

        private Light(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(id, student.id);
    }

}
