package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuliuhuan
 * @date 2022-11-19 20:09
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Energy {
    Long id;
    String name;
    double price;
    String date;
    double water;
}
