
package xyz.rexlin600.swagger.repository;

import xyz.rexlin600.swagger.model.Message;

import java.util.List;

/**
 * 消息操作接口
 *
 * @author rexlin600
 */
public interface MessageRepository {

    /**
     * 查找列表
     *
     * @return
     */
    List<Message> findAll();

    /**
     * 保存对象
     *
     * @param message
     * @return
     */
    Message save(Message message);

    /**
     * 更新对象
     *
     * @param message
     * @return
     */
    Message update(Message message);

    /**
     * 更新文本
     *
     * @param message
     * @return
     */
    Message updateText(Message message);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    Message findMessage(Long id);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void deleteMessage(Long id);

}
