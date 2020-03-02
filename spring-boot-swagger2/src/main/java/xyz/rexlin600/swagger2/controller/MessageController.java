
package xyz.rexlin600.swagger2.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.swagger2.config.BaseResult;
import xyz.rexlin600.swagger2.model.Message;
import xyz.rexlin600.swagger2.repository.MessageRepository;

import java.util.List;

/**
 * @author rexlin600
 * @menu Swagger2-消息
 */
@Api(value = "消息", protocols = "http")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * 1. 【消息-消息列表】
     *
     * @return
     */
    @ApiOperation(
            value = " 1. 【消息-消息列表】",
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
     * 2. 【消息-添加消息】
     *
     * @param message
     * @return
     */
    @ApiOperation(
            value = "2. 【消息-添加消息】",
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
     * 3. 【消息-修改消息】
     *
     * @param message
     * @return
     */
    @ApiOperation(
            value = "3. 【消息-修改消息】",
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
     * 4. 【消息-文本消息】
     *
     * @param message
     * @return
     */
    @PatchMapping(value = "/text")
    public BaseResult<Message> patch(Message message) {
        Message messageResult = this.messageRepository.updateText(message);
        return BaseResult.successWithData(messageResult);
    }

    /**
     * 5. 【消息-根据ID获取消息】
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Message get(@PathVariable Long id) {
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    /**
     * 6. 【消息-根据ID删除消息】
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }


}
