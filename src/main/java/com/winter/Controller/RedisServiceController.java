package com.winter.Controller;

import com.winter.service.project.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class RedisServiceController {
    @Autowired
    private RedisServiceImpl redisService;

//    @Autowired
//    private TestRedis testRedis;
    @Autowired
    private Jedis jedis;
    //

    @RequestMapping(value = "/setredis")
    public String setredis(String keyredis){
        redisService.setStr(keyredis,"2018年1月26日");
        return "保存成功,请访问getredis查询redis";
    }

    @RequestMapping(value = "/getredis")
    public String getredis(String keyredis){
        String getredis = (String) redisService.getKey(keyredis);
        return "redis的key是===>"+getredis;
    }


    @RequestMapping(value = "/delredis")
    public String delredis(String keyredis){
        redisService.delKey(keyredis);
        return "删除成功，请通过getredis进行查询";
    }

    @RequestMapping(value = "/testRedisAdd")
    public String testRedisAdd(){
        jedis.set("name", "xinxin");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name", "liuling", "age", "23", "qq", "476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
        return "success";
    }
//    @RequestMapping(value = "/testRedisMap")
//    public String testRedisMap(){
//        testRedis.testMap();
//        return "testRedisMap";
//    }
//    @RequestMapping(value = "/testRedisAdd")
//    public String testSet(){
//        testRedis.testSet();
//        return "testSet";
//    }
//    @RequestMapping(value = "/testString")
//    public String testString(){
//        testRedis.testString();
//        return "testString";
//    }
}
