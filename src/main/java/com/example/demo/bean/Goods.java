package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuliuhuan
 * @date 2022-11-12 18:02
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private long id;

    private String name;

    private int number;

    private double price;

}
