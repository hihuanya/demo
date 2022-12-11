package com.example.demo.service;

import com.example.demo.bean.Energy;


import java.util.Map;

/**
 * @author chuliuhuan
 * @date 2022-11-19 20:19
 */
public interface EnergyService {
    /**
     * 初始化数据
     */
    public void initEnergyData();

    public Map<String, Energy> queryEnergyListByDate();
}
