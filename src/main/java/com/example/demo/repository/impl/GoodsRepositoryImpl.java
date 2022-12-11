package com.example.demo.repository.impl;

import com.example.demo.bean.Goods;
import com.example.demo.repository.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author chuliuhuan
 * @date 2022-10-30 19:01
 */
@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

    Logger logger = (Logger) LoggerFactory.getLogger(getClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Goods goods) {
        return jdbcTemplate.update("INSERT INTO goods(name, number, price) values(?, ?, ?)",
                goods.getName(),goods.getNumber(),goods.getPrice());
    }

    @Override
    public int update(Goods goods) {
        return jdbcTemplate.update("UPDATE goods SET name = ? , number = ? , price = ?  WHERE id=?", goods.getName(),goods.getNumber(),goods.getPrice(),goods.getId());
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM goods where id = ? ",id);
    }

    @Override
    public Goods findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM goods WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<Goods>(Goods.class));
    }

    @Override
    public int reduceStock(long id) {
        int update = jdbcTemplate.update("UPDATE goods SET  number = number - 1  WHERE id=?  and  number > 0 ", id);
        if(update > 0){
            logger.info(Thread.currentThread()+"减少货存");
        }
        return update;
    }




}
