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
 * @since: 2020/5/7
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTodayResponse {

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    /**
     * error_code
     */
    private Integer errorCode;

    /**
     * reason
     */
    private String reason;

    /**
     * result
     */
    private List<Result> result;

    @ToString
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Result {

        /**
         * day
         */
        private Integer day;

        /**
         * des
         */
        private String des;

        /**
         * _id
         */
        @SuppressWarnings("AlibabaAvoidStartWithDollarAndUnderLineNaming")
        private String id;

        /**
         * lunar
         */
        private String lunar;

        /**
         * month
         */
        private Integer month;

        /**
         * pic
         */
        private String pic;

        /**
         * title
         */
        private String title;

        /**
         * year
         */
        private Integer year;

    }

}