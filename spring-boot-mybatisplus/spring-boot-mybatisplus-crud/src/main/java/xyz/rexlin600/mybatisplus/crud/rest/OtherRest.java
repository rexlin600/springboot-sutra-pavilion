package xyz.rexlin600.mybatisplus.crud.rest;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.mybatisplus.crud.model.OtherGetReq;

/**
 * 其他测试 Rest 接口
 *
 * @author: hekunlin
 * @since: 2020/6/28
 */
@Slf4j
@RestController
@RequestMapping(value = "/other")
public class OtherRest {

    /**
     * GET HTTP method，使用 @RequestBody 接收参数
     *
     * @param req
     * @return
     */
    @GetMapping("/get")
    public R get(@RequestBody OtherGetReq req) {
        log.info("==>  GET METHOD Params is : {}", req.toString());
        return R.ok(null);
    }

}