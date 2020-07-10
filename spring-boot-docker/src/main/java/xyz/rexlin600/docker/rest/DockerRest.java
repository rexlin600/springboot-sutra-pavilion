package xyz.rexlin600.docker.rest;

import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.docker.entity.PostReq;

/**
 * Docker rest
 *
 * @author hekunlin
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerRest {

	/**
	 * HELLO_WORLD
	 */
	private static String HELLO_WORLD = "hello world";

	/**
	 * Get string
	 *
	 * @return the string
	 */
	@GetMapping("/get")
	public String get() {
		return HELLO_WORLD.toUpperCase();
	}

	/**
	 * Post string
	 *
	 * @param postReq post req
	 * @return the string
	 */
	@PostMapping("/post")
	public String post(@RequestBody PostReq postReq) {
		return HELLO_WORLD.toUpperCase() + " : name=" + postReq.getName().toUpperCase() + " ,age=" + postReq.getAge();
	}

	/**
	 * Put string
	 *
	 * @param id id
	 * @return the string
	 */
	@PutMapping("put/{id}")
	public String put(@PathVariable(value = "id") Long id) {
		return HELLO_WORLD.toUpperCase() + " : " + id;
	}

	/**
	 * Delete string
	 *
	 * @param id id
	 * @return the string
	 */
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		return HELLO_WORLD.toUpperCase() + " : " + id;
	}


}