package xyz.rexlin600.docker.rest;

import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.docker.entity.PostReq;

/**
 * Docker
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
     * @return {@link String}
     */
    @GetMapping("/get")
    public String get() {
        return HELLO_WORLD.toUpperCase();
    }

    /**
     * POST请求
     *
     * @param postReq 请求类
     * @return {@link String}
     */
    @PostMapping("/post")
    public String post(@RequestBody PostReq postReq) {
        return HELLO_WORLD.toUpperCase() + " : name=" + postReq.getName().toUpperCase() + " ,age=" + postReq.getAge();
    }

    /**
     * PUT请求
     *
     * @param id ID
     * @return {@link String}
     */
    @PutMapping("put/{id}")
    public String put(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }

    /**
     * DELETE请求
     *
     * @param id ID
     * @return {@link String}
     */
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return HELLO_WORLD.toUpperCase() + " : " + id;
    }


}