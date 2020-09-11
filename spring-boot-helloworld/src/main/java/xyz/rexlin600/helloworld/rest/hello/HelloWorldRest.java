package xyz.rexlin600.helloworld.rest.hello;

import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.helloworld.entity.req.PostReq;

/**
 * HelloWorld 接口
 *
 * @author hekunlin
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloWorldRest {

	/**
	 * HELLO_WORLD
	 */
	private static String HELLO_WORLD = "hello world";

	/**
	 * 获取字符串
	 *
	 * @return the string
	 */
	@GetMapping("/get")
	public String get() {
		return HELLO_WORLD.toUpperCase();
	}

	/**
	 * POST接口测试
	 *
	 * @param postReq post req
	 * @return the string
	 */
	@PostMapping("/post")
	public String post(@RequestBody PostReq postReq) {
		return HELLO_WORLD.toUpperCase() + " : name=" + postReq.getName().toUpperCase() + " ,age=" + postReq.getAge();
	}

	/**
	 * 更新字符串
	 *
	 * @param id id
	 * @return the string
	 */
	@PutMapping("put/{id}")
	public String put(@PathVariable(value = "id") Long id) {
		return HELLO_WORLD.toUpperCase() + " : " + id;
	}

	/**
	 * 删除字符串
	 *
	 * @param id id
	 * @return the string
	 */
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		return HELLO_WORLD.toUpperCase() + " : " + id;
	}


}