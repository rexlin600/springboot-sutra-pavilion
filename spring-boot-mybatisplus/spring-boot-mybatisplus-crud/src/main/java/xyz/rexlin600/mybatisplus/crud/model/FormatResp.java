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
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatResp {

    // -----------------------------------------------------------------------------------------------
    // DATE
    // -----------------------------------------------------------------------------------------------

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date searchDate;

    // -----------------------------------------------------------------------------------------------
    // LocalDateTime
    // -----------------------------------------------------------------------------------------------

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime localUpdateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localSearchDate;

}