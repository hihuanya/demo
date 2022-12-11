package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author chuliuhuan
 * @date 2022-11-19 20:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@PropertySource("classpath:applicationContext-jedis.xml")
public class EnergyServiceImplTest {

    @Autowired
    EnergyServiceImpl energyServiceImpl;

    @Test
    public  void  initEnergyData() {
        energyServiceImpl.initEnergyData();
    }
    @Test
    public  void  queryEnergyListByDate() {
        energyServiceImpl.queryEnergyListByDate();
    }


}