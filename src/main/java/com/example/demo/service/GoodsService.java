package com.example.demo.service;

import com.example.demo.bean.Goods;

/**
 * @author chuliuhuan
 * @date 2022-11-12 19:22
 */
public interface GoodsService {

    /**
     * 购买商品
     */
    public Boolean buyGoods(long id);

    /**
     * 买商品
     *
     */
    public Boolean buySomeThing(long id);

    /**
     * 查看商品信息
     */
    public Goods queryGoodsById(Long id);

}
