package com.example.demo.service.impl;

import com.example.demo.bean.Goods;
import com.example.demo.commons.JsonUtils;
import com.example.demo.jedisdao.impl.JedisDaoSingleImpl;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chuliuhuan
 * @date 2022-11-12 19:22
 */
@Component
public class GoodsServiceImpl  implements GoodsService {

    Logger logger = (Logger) LoggerFactory.getLogger(getClass());


    /**
     * redis缓存 商品前缀
     */
    public static String REDIS_GOODS_PRIFX ="REDIS_GOODS_PRIFX";

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    JedisDaoSingleImpl jedisDaoSingle;

    @Override
    public Boolean buyGoods(long id) {
        int number=0;
        Goods goods = queryGoodsById(id);
        if( goods == null  || (number = goods.getNumber() )< 1){
            return false;
        }
        logger.info(Thread.currentThread()+"剩余库存数为：" + number);
        return  goodsRepository.reduceStock(id) > 0;
    }


    @Override
    public Boolean buySomeThing(long id) {
        return reduceStock(id);
    }

    @Override
    public Goods queryGoodsById(Long id) {
        try{
            String json = jedisDaoSingle.get(REDIS_GOODS_PRIFX+":"+id);
            //在缓存中是否命中
            if (json != null && json.length()>0) {//命中
//                logger.info("-----------------调用查看redis缓存操作--------------------");
                return JsonUtils.jsonToPojo(json, Goods.class);
            }
        }catch (Exception e){// 保证不影响功能正常实现，需要用try。。。catch
            e.printStackTrace();
        }
        //查询数据库
        Goods goods = goodsRepository.findById(id);
//        logger.info("==================查询mysql并放入redis缓存=========================");

        try {
            //放入redis中
            String objectToJson = JsonUtils.toJSon(goods);
            jedisDaoSingle.set(REDIS_GOODS_PRIFX+":"+id, objectToJson);
            //如果设置失败，最好clean一下
            jedisDaoSingle.expire(REDIS_GOODS_PRIFX+":"+id,60);
            //jedisPool.getResource().expire(redis_Key, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }


    public boolean reduceStock(Long id){
        Goods goods = queryGoodsById(id);
        int number = 0;
        if (goods == null || (number = goods.getNumber() ) < 1) {
            return false;
        }
        logger.info(Thread.currentThread()+"剩余库存数为：" + number);
        goods.setNumber(number - 1);
        goodsRepository.update(goods);
        jedisDaoSingle.del(REDIS_GOODS_PRIFX+":"+id);
        return true;
    }

}
