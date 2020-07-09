package xyz.rexlin600.kafka.constant;

/**
 * Kafka Topic 常量
 * <p>
 * 为什么说 Java Interface 不是常量存放的最佳地点：https://www.ibm.com/developerworks/cn/java/l-java-interface/index.html
 *
 * @author: hekunlin
 * @since: 2020/7/2
 */
public class KafkaTopicConstant {

    /**
     * 测试 sync Topic
     */
    public static final String SYNC_TOPIC = "rexlin600-sync";

    /**
     * 测试 async Topic
     */
    public static final String ASYNC_TOPIC = "rexlin600-async";


}