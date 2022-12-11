package com.example.demo.controller;

import com.example.demo.bean.Energy;
import org.springframework.web.bind.annotation.*;

/**
 * @author chuliuhuan
 * @date 2022-11-20 15:14
 */
@RestController
@RequestMapping("/energy")
public class PostmanController {

    @PutMapping("/putRequest")
    public Energy energy1(Energy energy) {
        return energy;
    }

    @PostMapping("/PostRequest")
    public Energy energy2(@RequestBody Energy energy) {
        return energy;
    }

    @DeleteMapping("/delete/{id}")
    public Energy deleteRequest(@PathVariable Long id) {
        return Energy.builder().id(id).water(33).price(23434).name("energy323:DeleteMapping").date("2022-12-31").build();
    }

    @GetMapping("/getRequest")
    public Energy deleteRequest(@RequestBody Energy energy) {
        return energy;
    }


}
