package xyz.rexlin600.java8.date.api;

import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * InstantApi 类
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@Slf4j
public class InstantApi {

    /**
     * 当前时间戳
     */
    public static void now() {
        Instant now0 = Instant.now();
        Instant now1 = Instant.now(Clock.systemDefaultZone());
        Instant now2 = Instant.now(Clock.system(ZoneId.systemDefault()));

        log.info("==> now0 instant is [{}]", now0);
        log.info("==> now1 instant is [{}]", now1);
        log.info("==> now2 instant is [{}]", now2);
    }

    /**
     * 秒、毫秒、纳秒转换
     */
    public static void mill() {
        // 将此瞬间转换为从1970-01-01T00：00：00Z的纪元*开始的毫秒数
        long epochMilli = Instant.now().toEpochMilli();
        // 从1970-01-01T00：00：00Z的Java纪元获取秒数
        long epochSecond = Instant.now().getEpochSecond();
        // 从秒的开始，沿时间轴获取纳秒数
        int nano = Instant.now().getNano();

        log.info("==>  epochMilli is [{}]", epochMilli);
        log.info("==>  epochSecond is [{}]", epochSecond);
        log.info("==>  nano is [{}]", nano);
    }

    /**
     * 构建时间戳 Instant
     */
    public static void build() {
        long epochMilli = Instant.now().toEpochMilli();
        long epochSecond = Instant.now().getEpochSecond();

        Instant instant0 = Instant.ofEpochMilli(epochMilli);
        Instant instant1 = Instant.ofEpochSecond(epochSecond);
        Instant instant2 = Instant.ofEpochSecond(epochSecond, 100L);

        log.info("==>  build instant0 is [{}]", instant0);
        log.info("==>  build instant1 is [{}]", instant1);
        log.info("==>  build instant2 is [{}]", instant2);
    }

    /**
     * 时间戳比较
     */
    public static void compare() {
        long epochMilli = Instant.now().toEpochMilli();
        long suf = epochMilli + 100L;
        long pre = epochMilli - 100L;

        boolean before = Instant.now().isBefore(Instant.ofEpochMilli(pre));
        boolean after = Instant.now().isAfter(Instant.ofEpochMilli(suf));

        log.info("==>  compare result before is [{}]", before);
        log.info("==>  compare result after is [{}]", after);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        now();
        System.out.println("---------------------------");
        mill();
        System.out.println("---------------------------");
        build();
        System.out.println("---------------------------");
        compare();
    }

}