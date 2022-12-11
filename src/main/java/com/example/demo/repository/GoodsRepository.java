package com.example.demo.repository;


import com.example.demo.bean.Goods;

/**
 * @author chuliuhuan
 * @date 2022-10-30 19:01
 */
public interface GoodsRepository {
    int save(Goods student);
    int update(Goods student);
    int delete(long id);
    Goods findById(long id);

    /**
     * 减库存
     */
    int reduceStock(long id);

}
