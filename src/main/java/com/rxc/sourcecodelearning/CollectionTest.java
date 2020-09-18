package com.rxc.sourcecodelearning;

import lombok.Data;

import java.util.TreeSet;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/18 17:51
 */
public class CollectionTest {

    public static void main(String[] args) {
        TreeSet<Employee> employees = new TreeSet<>((o1, o2) -> {
            int result = Integer.compare(o1.birthday.year, o2.birthday.getYear());
            result = result == 0 ? Integer.compare(o1.birthday.getMonth(), o2.birthday.getMonth()) : result;
            result = result == 0 ? Integer.compare(o1.birthday.getDay(), o2.birthday.getDay()) : result;
            return result;
        });
        Employee e1 = new Employee("jack", 18, new MyData(2002, 9, 10));
        Employee e2 = new Employee("mary", 19, new MyData(2001, 9, 18));
        Employee e3 = new Employee("jane", 18, new MyData(2002, 9, 18));
        Employee e4 = new Employee("tom", 20, new MyData(2000, 9, 18));
        Employee e5 = new Employee("mick", 21, new MyData(1999, 9, 18));
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    @Data
    static class Employee implements Comparable {
        String name;
        int age;
        MyData birthday;

        public Employee(String name, int age, MyData birthday) {
            this.name = name;
            this.age = age;
            this.birthday = birthday;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Employee) {
                Employee employee = (Employee) o;
                return name.compareTo(employee.name);
            }
            throw new RuntimeException("进行Employee类对象比较时类型不匹配");
        }
    }

    @Data
    static class MyData {
        private int year;
        private int month;
        private int day;

        public MyData(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}
