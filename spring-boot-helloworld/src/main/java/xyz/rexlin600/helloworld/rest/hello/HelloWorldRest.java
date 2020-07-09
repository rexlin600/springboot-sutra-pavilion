package xyz.rexlin600.helloworld.rest.hello;

import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.helloworld.entity.req.PostReq;

/**
 * HelloWorld
 *
 * @author: hekunlin
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloWorldRest {

    private static String HELLO_WORLD = "hello world";

    /**
     * HelloWorld GET请求
     *
     * @return 字符串
     */
    @GetMapping("/get")
    public String get() {
        return HELLO_WORLD.toUpperCase();
    }

    /**
     * HelloWorld POST请求
     *
     * @param postReq 请求类
     * @return 字符串
     */
    @PostMapping("/post")
    public String post(@RequestBody PostReq postReq) {
        return HELLO_WORLD.toUpperCase() + " : name=" + postReq.getName().toUpperCase() + " ,age=" + postReq.getAge();
    }

    /**
     * HelloWorld PUT请求
     *
     * @param id ID
     * @return 字符串
     */
    @PutMapping("put/{id}")
    public String put(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }

    /**
     * HelloWorld  DELETE请求
     *
     * @param id ID
     * @return 字符串
     */
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }


}