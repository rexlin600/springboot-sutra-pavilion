package xyz.rexlin600.mybatisplus.crud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间接收与转换
 *
 * @author: hekunlin
 * @date: 2020/6/4
 */
@SuppressWarnings({"ALL", "AlibabaRemoveCommentedCode"})
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Format {

    // -----------------------------------------------------------------------------------------------
    // Date
    // 下面展示 req -> resp Date 类型数据使用下面 DateTimeFormat 与 JsonFormat 的互转
    // -----------------------------------------------------------------------------------------------

    /**
     * @DateTimeFormat to @DateTimeFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * @DateTimeFormat to @JsonFormat
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteDate;

    /**
     * @JsonFormat to @JsonFormat
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * @JsonFormat to @DateTimeFormat
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date searchDate;

    // -----------------------------------------------------------------------------------------------
    // LocalDateTime
    // -----------------------------------------------------------------------------------------------

    /**
     * @DateTimeFormat to @DateTimeFormat
     *
     * LocalDateTime 通过 @DateTimeFormat 接收参数会报错
     */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //private LocalDateTime localCrateDate;

    /**
     * @DateTimeFormat to @JsonFormat
     *
     * LocalDateTime 通过 @DateTimeFormat 接收参数会报错
     */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //private LocalDateTime localDeleteDate;

    /**
     * @JsonFormat to @JsonFormat
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime localUpdateDate;

    /**
     * @JsonFormat to @DateTimeFormat
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localSearchDate;

}