package xyz.rexlin600.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 测试Blog类
 *
 * @author: hekunlin
 * @date: 2020/1/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Blog implements Serializable {

    /**
     * ID
     */
    @NotNull
    private Long id;

    /**
     * 标题
     */
    @NotEmpty
    private String title;

    /**
     * 内容
     */
    @NotEmpty
    private String content;

    /**
     * 是否受欢迎
     */
    @NotNull
    private boolean popular;

    /**
     * 创建时间
     */
    private String createDate;

}