package com.example.demo.repository;


import com.example.demo.bean.Student;
import com.example.demo.bean.User;

/**
 * @author chuliuhuan
 * @date 2022-10-30 19:01
 */
public interface StudentRepository {
    int save(Student student);
    int update(Student student);
    int delete(long id);
    User findById(long id);

    User findByName(String name);

    User findByNameAndPwd(String name,String pwd);
}
