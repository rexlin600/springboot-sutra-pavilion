package xyz.rexlin600.validation.rest;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.validation.common.apiparam.Response;
import xyz.rexlin600.validation.common.apiparam.ResponseGenerator;
import xyz.rexlin600.validation.param.*;
import xyz.rexlin600.validation.param.group.Age;
import xyz.rexlin600.validation.param.group.Classes;
import xyz.rexlin600.validation.param.group.Name;

import javax.validation.groups.Default;

/**
 * Bean 校验接口
 *
 * @author hekunlin
 */
@RestController
@RequestMapping("/bean/validation")
public class BeanValidationRest {

	/**
	 * 非NULL校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkNull")
	public Response<Void> checkNull(@RequestBody @Validated NullReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 布尔值校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkBoolean")
	public Response<Void> checkBoolean(@RequestBody @Validated BooleanReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 数字校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkNumberReq")
	public Response<Void> checkNumberReq(@RequestBody @Validated NumberReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 长度校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkLengthReq")
	public Response<Void> checkLengthReq(@RequestBody @Validated LengthReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 范围校验（数字、字符串）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkRangeReq")
	public Response<Void> checkRangeReq(@RequestBody @Validated RangeReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 其他校验（邮箱、信用卡）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkOtherReq")
	public Response<Void> checkOtherReq(@RequestBody @Validated OtherReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 自定义注解校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/checkCustomReq")
	public Response<Void> checkCustomReq(@RequestBody @Validated CustomReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}


	/**
	 * 日期校验
	 *
	 * @param req the req
	 * @return the response
	 */
	@PostMapping("/checkDateReq")
	public Response<Void> checkDateReq(@RequestBody @Validated DateReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	// -----------------------------------------------------------------------------------------------
	// 分组校验
	// -----------------------------------------------------------------------------------------------

	/**
	 * 分组校验1（Name）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/group1")
	public Response<Void> group1(@RequestBody @Validated(Name.class) GroupReq req) {

		// 本示例会校验 name、list 字段（Name.class 继承了 Default）

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	/**
	 * 分组校验2（Classes）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/group2")
	public Response<Void> group2(@RequestBody @Validated(Classes.class) GroupReq req) {

		// 本示例会校验 clasess、list 字段（Classes.class 继承了 Default）

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	/**
	 * 分组校验3（Age）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/group3")
	public Response<Void> group3(@RequestBody @Validated(Age.class) GroupReq req) {

		// 本示例只会校验 age 字段；因为 Age.class 没有继承 Default，因此不会校验 list 字段！！！（Age.class 没有继承了 Default）

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	/**
	 * 分组校验4（Default)
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/group4")
	public Response<Void> group4(@RequestBody @Validated({Default.class}) GroupReq req) {

		// 本示例只会校验 list 字段

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	/**
	 * 分组校验5（All）
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/group5")
	public Response<Void> group5(@RequestBody @Validated({Name.class, Classes.class, Age.class}) GroupReq req) {

		// 本示例会校验 name、clasess、age、list 字段

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

	// -----------------------------------------------------------------------------------------------
	// 复杂校验
	// -----------------------------------------------------------------------------------------------

	/**
	 * 脚本校验
	 *
	 * @param req req
	 * @return the response
	 */
	@PostMapping("/script")
	public Response<Void> script(@RequestBody @Validated ScriptReq req) {

		// 后续逻辑 ...

		return ResponseGenerator.success();
	}

}

