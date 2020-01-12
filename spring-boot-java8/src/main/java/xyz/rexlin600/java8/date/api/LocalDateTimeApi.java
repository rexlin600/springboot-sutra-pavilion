package xyz.rexlin600.java8.date.api;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.chrono.Chronology;
import java.util.Date;

/**
 * LocalDateTimeApi 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */

@Slf4j
public class LocalDateTimeApi {

    /**
     * 当前时间
     */
    public void now() {
        LocalDateTime.now();

        // 系统默认时区
        LocalDateTime.now(ZoneId.systemDefault());

        // 获取东八区时间
        LocalDateTime.now(ZoneId.of("Asia/Shanghai"));

        // 时区偏移量
        LocalDateTime.now(ZoneId.ofOffset("GMT", ZoneOffset.of("+08:00")));

        LocalDateTime.now(Clock.systemDefaultZone());
        LocalDateTime.now(Clock.system(ZoneId.systemDefault()));
    }

    /**
     * 时间转换
     */
    public void transfer() {
        // transfer LocalDate
        LocalDateTime.now().toLocalDate();

        // transfer LocalTime
        LocalDateTime.now().toLocalTime();

        // transfer Instant
        LocalDateTime.now().toInstant(ZoneOffset.ofHours(8));
        // transfer Instant
        LocalDateTime.now().toInstant(ZoneOffset.of("+08:00"));

        // transfer EpochSecond：将此日期时间转换为从1970-01-01T00：00：00Z的纪元*开始的秒数
        LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));

        // transfer String
        LocalDateTime.now().toString();

        // transfer OffsetDateTime
        LocalDateTime.now().atOffset(ZoneOffset.ofHours(8));

        // LocalDateTime transfer Date
        // Instant transfer Date
        Instant instant = LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.ofHours(8));
        Date.from(instant);
    }

    /**
     * 获取具体时间
     */
    public void actual() {
        // 一年中的第几天
        int dayOfYear = LocalDateTime.now().getDayOfYear();
        // 一月中的第几天
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        // 一周中的第几天
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        // 一周中的第几天
        dayOfWeek.getValue();
        log.info("dayOfYear [{}]", dayOfYear);
        log.info("dayOfMonth [{}]", dayOfMonth);
        log.info("dayOfWeek [{}]", dayOfWeek);

        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        int monthValue = LocalDateTime.now().getMonthValue();
        // 获取此日期时间的时间顺序
        Chronology chronology = LocalDateTime.now().getChronology();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int second = LocalDateTime.now().getSecond();
        int nano = LocalDateTime.now().getNano();

        log.info("year [{}]", year);
        log.info("month [{}]", month);
        log.info("monthValue [{}]", monthValue);
        log.info("chronology [{}]", chronology);
        log.info("hour [{}]", hour);
        log.info("minute [{}]", minute);
        log.info("second [{}]", second);
        log.info("nano [{}]", nano);
    }

    /**
     * 构建时间
     */
    public void build() {
        LocalDateTime.of(2020, 1, 1, 12, 0);
        LocalDateTime.of(2020, 1, 1, 12, 0, 0);
        LocalDateTime.of(2020, 1, 1, 12, 0, 0, 0);
        LocalDateTime.of(2020, Month.JANUARY, 1, 1, 0);
        LocalDateTime.of(2020, Month.JANUARY, 1, 1, 0, 0);
        LocalDateTime.of(2020, Month.JANUARY, 1, 1, 0, 0, 0);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime.of(localDate, localTime);
    }

    /**
     * 时间比较
     */
    public void compare() {
        LocalDateTime pre = LocalDateTime.of(2020, 1, 1, 12, 0);
        LocalDateTime suf = LocalDateTime.of(2099, 1, 1, 12, 0);

        LocalDateTime.now(ZoneId.systemDefault()).isAfter(pre);
        LocalDateTime.now(ZoneId.systemDefault()).isBefore(suf);
    }


}