package com.example.demo.repository.impl;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Student;
import com.example.demo.bean.User;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author chuliuhuan
 * @date 2022-10-30 19:01
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        return jdbcTemplate.update("INSERT INTO Student(name, sex, age) values(?, ?, ?)",
                student.getName(),student.getSex(),student.getAge());
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("UPDATE Student SET name = ? , password = ? , age = ?  WHERE id=?", student.getName(),student.getSex(),student.getAge(),student.getId());
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM Student where id = ? ",id);
    }

    @Override
    public User findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));

//        return jdbcTemplate.queryForObject("SELECT * FROM Student WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<Student>(Student.class));
    }
    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE name=?", new Object[] { name }, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User findByNameAndPwd(String name, String pwd) {
        return jdbcTemplate.queryForObject("SELECT * FROM  user WHERE name=? and pwd =?  ", new Object[] { name ,pwd}, new BeanPropertyRowMapper<User>(User.class));

    }
}
