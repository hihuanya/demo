package com.example.demo.repository;


import com.example.demo.bean.Energy;

import java.util.List;

/**
 * @author chuliuhuan
 * @date 2022-11-19 20:05
 */
public interface EnergyRepository{
    int save(Energy energy);
    int update(Energy energy);
    int delete(long id);
    Energy findById(long id);

    List<Energy> queryEnergyListByDate();
}
