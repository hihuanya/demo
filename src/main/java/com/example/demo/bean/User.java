package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuliuhuan
 * @date 2022-11-20 17:18
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    String name;
    String pwd;
    String token;
}
