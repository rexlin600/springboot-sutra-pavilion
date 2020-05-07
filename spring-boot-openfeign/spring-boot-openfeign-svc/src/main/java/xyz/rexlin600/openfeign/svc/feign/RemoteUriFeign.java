package xyz.rexlin600.openfeign.svc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.rexlin600.openfeign.svc.config.FeignConfiguration;

/**
 * 远程URI Feign
 * <p>
 * 这个示例演示如何使用 Feign 客户端调用远程 URI，从而代替使用 HttpUtil 等方式去调用远程服务
 *
 * @author: hekunlin
 * @date: 2020/5/7
 */
@Component
@FeignClient(name = "remoteURI", url = "http://api.juheapi.com", configuration = FeignConfiguration.class)
public interface RemoteUriFeign {

    /**
     * 查看某月某日的事件列表
     *
     * @param key   聚合数据 在个人中心->我的数据,接口名称上方查看
     * @param v     版本，当前：1.0
     * @param month 月份，如：10
     * @param day   日，如：1
     * @return
     */
    @GetMapping("/japi/toh")
    String historyToady(@RequestParam(value = "key") String key,
                        @RequestParam(value = "v") String v,
                        @RequestParam(value = "month") Integer month,
                        @RequestParam(value = "day") Integer day);

}