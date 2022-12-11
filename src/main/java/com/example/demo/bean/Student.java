package com.example.demo.bean;

/**
 * @author chuliuhuan
 * @date 2022-11-12 17:59
 */
public class Student {
    private Long id;
    private String name;
    private int sex;
    private int age;
    private String password;

    public Student(){

    }
    public Student(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    // 省略get、set方法


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
