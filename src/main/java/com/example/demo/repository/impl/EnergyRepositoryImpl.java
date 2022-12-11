package com.example.demo.repository.impl;

import com.example.demo.bean.Energy;
import com.example.demo.repository.EnergyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chuliuhuan
 * @date 2022-11-19 20:15
 */
@Repository
public class EnergyRepositoryImpl implements EnergyRepository {

    Logger logger = (Logger) LoggerFactory.getLogger(getClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Energy energy) {
        return jdbcTemplate.update("INSERT INTO energy(id,name, price,date,water) values(?,?,?, ?,?)",
               energy.getId(), energy.getName(),energy.getPrice(),energy.getDate(),energy.getWater());
    }

    @Override
    public int update(Energy student) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public Energy findById(long id) {
        return null;
    }

    @Override
    public List<Energy> queryEnergyListByDate() {

        // 条件查询
        String sql = "SELECT * FROM energy WHERE id != ?";
        // 获取结果
        return jdbcTemplate.query(sql, new Object[]{22222222}, new BeanPropertyRowMapper<Energy>(Energy.class));
    }
}
