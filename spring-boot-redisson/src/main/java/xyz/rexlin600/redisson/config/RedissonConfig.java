package xyz.rexlin600.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.connection.RoundRobinDnsAddressResolverGroupFactory;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedissonConfig 程序化配置类
 *
 * <p>
 * 程序化配置方式，使用此种方式不需要在 yml 文件中再配置 redisson 相关配置
 *
 * @author: rexlin600
 * @date: 2020-02-05
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host:localhost}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private String port;

    /**
     * 集群 redisson 配置，本示例不启用
     *
     * @return
     */
    public RedissonClient cluster() {     //@Bean
        Config config = new Config();
        config.useClusterServers()
                // 集群状态扫描间隔时间，单位是毫秒
                .setScanInterval(2000)
                .setPassword(null)
                // 负载均衡算法
                .setLoadBalancer(new RoundRobinLoadBalancer())
                // 可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");
        config.setAddressResolverGroupFactory(new RoundRobinDnsAddressResolverGroupFactory());
        config.setThreads(0);
        config.setNettyThreads(0);
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        return Redisson.create(config);
    }

    /**
     * 主从 redisson 配置，本示例不启用
     *
     * @return
     */
    public RedissonClient master() { //@Bean
        Config config = new Config();
        config.useMasterSlaveServers()
                .setPassword(null)
                .setDatabase(0)
                //可以用"rediss://"来启用SSL连接
                //可以用"rediss://"来启用SSL连接
                .setMasterAddress("redis://127.0.0.1:6379")
                .addSlaveAddress("redis://127.0.0.1:6389", "redis://127.0.0.1:6332", "redis://127.0.0.1:6419")
                .addSlaveAddress("redis://127.0.0.1:6399");
        config.setAddressResolverGroupFactory(new RoundRobinDnsAddressResolverGroupFactory());
        config.setThreads(0);
        config.setNettyThreads(0);
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        return Redisson.create(config);
    }


    /**
     * 哨兵 redisson 配置，本示例不启用
     *
     * @return
     */
    public RedissonClient sentinel() {      //@Bean
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .setPassword(null)
                .setDatabase(0)
                //可以用"rediss://"来启用SSL连接
                .addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26319");
        config.setAddressResolverGroupFactory(new RoundRobinDnsAddressResolverGroupFactory());
        config.setThreads(0);
        config.setNettyThreads(0);
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        return Redisson.create(config);
    }


    /**
     * 单机 redisson 配置
     *
     * @return
     */
    @Bean
    public RedissonClient single() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(null)
                .setDatabase(0)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(32);
        config.setThreads(0);
        config.setNettyThreads(0);
        config.setCodec(new JsonJacksonCodec());
        config.setTransportMode(TransportMode.NIO);
        return Redisson.create(config);
    }

}