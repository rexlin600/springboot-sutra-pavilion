package xyz.rexlin600.mybatisplus.crud.rest;

import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.mybatisplus.crud.common.apiparam.Response;
import xyz.rexlin600.mybatisplus.crud.common.apiparam.ResponseGenerator;
import xyz.rexlin600.mybatisplus.crud.model.Format;
import xyz.rexlin600.mybatisplus.crud.model.FormatResp;


/**
 * 时间接收与转换
 *
 * @menu MybatisPlus-CRUD
 * @author: hekunlin
 * @date: 2020/6/4
 */
@Slf4j
@RestController
@RequestMapping("/format")
public class FormatRest {

    /**
     * 10. 【时间format】
     *
     * @param format
     * @return
     */
    @PostMapping("/convert")
    public Response<FormatResp> convert(@RequestBody Format format) {

        Format f = format;
        log.info("==>  convert Format class is : [{}]", f.toString());

        /**
         * 注意：Hutool 中的 BeanCopier 不能拷贝 LocalDateTime、只能拷贝 Date 并且只拷贝一次 Date 类型数据（估计是bug）
         *
         * 因此这里使用的是 Spring 框架提供的 BeanCopier
         */
        FormatResp formatResp = new FormatResp();
        BeanCopier.create(Format.class, FormatResp.class, false).copy(format, formatResp, null);

        // other thing

        return ResponseGenerator.success(formatResp);
    }


}