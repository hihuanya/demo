package com.example.demo.controller;

import com.example.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author chuliuhuan
 * @date 2022-10-19 22:14
 */
@RestController
@RequestMapping("/goods")
@Api(tags = {"商品接口"})
public class ControllerGoods {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello @springBo  ot1";
    }

    @ApiOperation(value="购买商品",notes = "传输ID 商品库存减一")
    @RequestMapping(value = "/buyGoods/{id}",method = RequestMethod.GET)
    public String bugGoods(@PathVariable Long id){
        return  goodsService.buyGoods(id)? "成功！":"失败！";
    }


    @ApiOperation(value="购买商品",notes = "传输ID 商品库存减一")
    @RequestMapping(value = "/buySomeThing/{id}",method = RequestMethod.GET)
    public String buySomeThing(@PathVariable Long id){
        return  goodsService.buySomeThing(id)? "成功！":"失败！";
    }






}
