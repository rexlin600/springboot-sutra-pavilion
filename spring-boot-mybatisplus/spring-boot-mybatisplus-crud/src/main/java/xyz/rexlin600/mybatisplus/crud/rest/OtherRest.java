package xyz.rexlin600.mybatisplus.crud.rest;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.mybatisplus.crud.model.OtherGetReq;

/**
 * Other rest
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping(value = "/other")
public class OtherRest {

	/**
	 * Get r
	 *
	 * @param req req
	 * @return the r
	 */
	@GetMapping("/get")
	public R get(@RequestBody OtherGetReq req) {
		log.info("==>  GET METHOD Params is : {}", req.toString());
		return R.ok(null);
	}

}