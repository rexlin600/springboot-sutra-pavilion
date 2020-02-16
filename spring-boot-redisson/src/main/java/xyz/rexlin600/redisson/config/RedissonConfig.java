//package xyz.rexlin600.gitlab.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.codec.JsonJacksonCodec;
//import org.redisson.config.Config;
//import org.redisson.config.TransportMode;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * RedissonConfig 配置类
// *
// * 可以实用配置直接代替
// *
// * @author: rexlin600
// * @date: 2020-02-05
// */
//@Configuration
//public class RedissonConfig {
//
//    @Value("${spring.redis.host:localhost}")
//    private String host;
//
//    @Value("${spring.redis.port:6379}")
//    private String port;
//
//    @Bean
//    public RedissonClient redisson() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://" + host + ":" + port)
//                .setDatabase(0)
//                .setConnectionPoolSize(8)
//                .setConnectionMinimumIdleSize(0);
//        config.setThreads(0);
//        config.setNettyThreads(0);
//        config.setCodec(new JsonJacksonCodec());
//        config.setTransportMode(TransportMode.NIO);
//        return Redisson.create(config);
//    }
//
//}