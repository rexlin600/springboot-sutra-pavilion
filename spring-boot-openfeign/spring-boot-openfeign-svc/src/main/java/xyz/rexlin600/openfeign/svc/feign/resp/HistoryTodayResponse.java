package xyz.rexlin600.openfeign.svc.feign.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 历史上的今天返回类
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTodayResponse {

    private Integer error_code;

    private String reason;

    private List<Result> result;

    @ToString
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Result {

        private Integer day;

        private String des;

        private String _id;

        private String lunar;

        private Integer month;

        private String pic;

        private String title;

        private Integer year;

    }

}