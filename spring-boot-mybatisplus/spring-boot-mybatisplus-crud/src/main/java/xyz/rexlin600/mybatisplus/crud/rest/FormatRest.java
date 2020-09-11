package xyz.rexlin600.mybatisplus.crud.rest;

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
 * MybatisPlus 对象转换测试接口
 *
 * @author hekunlin
 */
@Slf4j
@RestController
@RequestMapping("/format")
public class FormatRest {

	/**
	 * 对象转换
	 *
	 * @param format format
	 * @return the response
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