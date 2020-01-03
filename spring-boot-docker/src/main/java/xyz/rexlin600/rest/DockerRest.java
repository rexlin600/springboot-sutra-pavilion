package xyz.rexlin600.rest;

import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.entity.req.PostReq;

/**
 * Docker HelloWorld rest请求
 *
 * @author: hekunlin
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerRest {

    private static String HELLO_WORLD = "hello world";

    /**
     * GET请求
     *
     * @return
     */
    @GetMapping("/get")
    public String get() {
        return HELLO_WORLD.toUpperCase();
    }

    /**
     * POST请求
     *
     * @return
     */
    @PostMapping("/post")
    public String post(@RequestBody PostReq postReq) {
        return HELLO_WORLD.toUpperCase() + " : name=" + postReq.getName().toUpperCase() + " ,age=" + postReq.getAge();
    }

    /**
     * PUT请求
     *
     * @return
     */
    @PutMapping("put/{id}")
    public String put(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }

    /**
     * DELETE请求
     *
     * @return
     */
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }


}