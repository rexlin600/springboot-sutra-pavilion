
package xyz.rexlin600.swagger2.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.swagger2.config.BaseResult;
import xyz.rexlin600.swagger2.model.Message;
import xyz.rexlin600.swagger2.repository.MessageRepository;

import java.util.List;

/**
 * Message controller
 *
 * @author hekunlin
 */
@Api(value = "消息", protocols = "http")
@RestController
@RequestMapping("/message")
public class MessageController {

	/**
	 * Message repository
	 */
	@Autowired
	private MessageRepository messageRepository;

	/**
	 * List list
	 *
	 * @return the list
	 */
	@ApiOperation(
			value = " 1. 消息-消息列表]",
			notes = "完整的消息内容列表",
			produces = "application/json, application/xml",
			consumes = "application/json, application/xml",
			response = List.class)
	@GetMapping(value = "/list")
	public List<Message> list() {
		List<Message> messages = this.messageRepository.findAll();
		return messages;
	}

	/**
	 * Create message
	 *
	 * @param message message
	 * @return the message
	 */
	@ApiOperation(
			value = "2. 消息-添加消息]",
			notes = "根据参数创建消息"
	)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
	})
	@PostMapping(value = "")
	public Message create(Message message) {
		System.out.println("====" + message.toString());
		message = this.messageRepository.save(message);
		return message;
	}


	/**
	 * Modify message
	 *
	 * @param message message
	 * @return the message
	 */
	@ApiOperation(
			value = "3. 消息-修改消息]",
			notes = "根据参数修改消息"
	)
	@PutMapping(value = "")
	@ApiResponses({
			@ApiResponse(code = 100, message = "请求参数有误"),
			@ApiResponse(code = 101, message = "未授权"),
			@ApiResponse(code = 103, message = "禁止访问"),
			@ApiResponse(code = 104, message = "请求路径不存在"),
			@ApiResponse(code = 200, message = "服务器内部错误")
	})
	public Message modify(Message message) {
		Message messageResult = this.messageRepository.update(message);
		return messageResult;
	}

	/**
	 * Patch base result
	 *
	 * @param message message
	 * @return the base result
	 */
	@PatchMapping(value = "/text")
	public BaseResult<Message> patch(Message message) {
		Message messageResult = this.messageRepository.updateText(message);
		return BaseResult.successWithData(messageResult);
	}

	/**
	 * Get message
	 *
	 * @param id id
	 * @return the message
	 */
	@GetMapping(value = "/{id}")
	public Message get(@PathVariable Long id) {
		Message message = this.messageRepository.findMessage(id);
		return message;
	}

	/**
	 * Delete *
	 *
	 * @param id id
	 */
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
	}


}
