package com.example.demo.service.impl;

import com.example.demo.bean.Energy;
import com.example.demo.repository.EnergyRepository;
import com.example.demo.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chuliuhuan
 * @date 2022-11-19 20:23
 */
@Component
public class EnergyServiceImpl implements EnergyService {

    @Autowired
    EnergyRepository energyRepository;

    public static ArrayList<String>  deviceList= new ArrayList<String>(10);
    public static ArrayList<String>  dateList= new ArrayList<String>(11);

    static {
        deviceList.add("设备1");
        deviceList.add("设备2");
        deviceList.add("设备3");
        deviceList.add("设备4");
        deviceList.add("设备5");
        deviceList.add("设备6");
        deviceList.add("设备7");
        deviceList.add("设备8");
        deviceList.add("设备9");
        deviceList.add("设备10");
        deviceList.add("设备11");



        dateList.add("01-01");
        dateList.add("01-02");
        dateList.add("01-03");
        dateList.add("01-04");
        dateList.add("01-05");
        dateList.add("01-06");
        dateList.add("01-07");
        dateList.add("01-08");
        dateList.add("01-09");
        dateList.add("01-10");
        dateList.add("01-11");
    }

    /**
     * 初始化表数据
     */
    @Override
    public void initEnergyData() {
        Random r = new Random();
        for (int i = 1; i < 2001; i++) {
            int random = r.nextInt(10);
            int dataRandom = r.nextInt(10);

            energyRepository.save(
                    Energy.builder().id((long) i).name(deviceList.get(random)).price(i+random).date(dateList.get(dataRandom)).water(random * 2).build()
            );

        }
    }

    /**
     * 查询表里数据并对数据进行分组 后合并
     * @return
     */
    @Override
    public Map<String, Energy> queryEnergyListByDate() {

        List<Energy> dataBase =  energyRepository.queryEnergyListByDate();
        // group by  分组后结果
        Map<String, List<Energy>> collect = dataBase.stream().collect(Collectors.groupingBy(energy -> energy.getDate()));

        // 要返回的数据
        Map<String, Energy> energyMap = new HashMap<>();
        // 遍历map
        dataBase.stream().collect(Collectors.groupingBy(energy -> energy.getDate())).forEach((k,v)-> {
            v.stream().forEach(groupByEnergy -> {
                if (energyMap.get(k) == null) {
                    energyMap.put(k,
                            Energy.builder()
                                    .id(groupByEnergy.getId())
                                    .date(groupByEnergy.getDate())
                                    .price(groupByEnergy.getPrice())
                                    .name(groupByEnergy.getName())
                                    .water(groupByEnergy.getWater())
                                    .build());
                }else {
                    energyMap.get(k).setPrice(groupByEnergy.getPrice()+energyMap.get(k).getPrice());
                    energyMap.get(k).setWater(groupByEnergy.getWater()+energyMap.get(k).getWater());
                }
            } );

        });
        collect.size();
        return energyMap;
    }


}
