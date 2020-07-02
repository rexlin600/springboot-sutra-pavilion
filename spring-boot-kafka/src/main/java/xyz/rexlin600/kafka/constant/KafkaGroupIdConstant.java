package xyz.rexlin600.kafka.constant;

/**
 * Kafka 消费者组常量
 *
 * @author: hekunlin
 * @date: 2020/7/2
 */
public class KafkaGroupIdConstant {

    /**
     * 同步消息 GROUP_ID
     */
    public static final String SYNC_GROUP_ID = "sync-group-" + KafkaTopicConstant.TOPIC;

    /**
     * 异步消息 GROUP_ID
     */
    public static final String ASYNC_GROUP_ID = "async-group-" + KafkaTopicConstant.TOPIC;

}