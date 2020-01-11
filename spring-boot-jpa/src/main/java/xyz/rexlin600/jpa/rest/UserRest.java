package xyz.rexlin600.docker.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.github.common.apiparam.PageResult;
import xyz.rexlin600.github.common.apiparam.Response;
import xyz.rexlin600.github.common.apiparam.ResponseGenerator;
import xyz.rexlin600.docker.entity.User;
import xyz.rexlin600.swagger.repository.UserRepository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @menu JPA用户API
 * @author: hekunlin
 * @date: 2020/1/8
 */
@RestController
@RequestMapping("/jpa")
public class UserRest {

    /**
     * 说明：为了方便，我就直接省略service
     */
    @Resource
    private UserRepository userRepository;

    /**
     * 1. 【用户列表】
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public Response<PageResult<User>> list(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "size") Integer size) {
        Page<User> all = userRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setTotalPage(all.getTotalPages());
        pageResult.setList(all.getContent());
        pageResult.setPage(page);
        pageResult.setSize(size);
        pageResult.setTotalCount(all.getTotalElements());
        return ResponseGenerator.success(pageResult);
    }

    /**
     * 2. 【新增用户】
     *
     * @param user
     * @return
     */
    @PostMapping
    public Response add(@RequestBody User user) {
        userRepository.save(user);
        return ResponseGenerator.success();
    }

    /**
     * 3. 【修改用户】
     *
     * @param user
     * @return
     */
    @PutMapping
    public Response update(@RequestBody User user) {
        userRepository.saveAndFlush(user);
        return ResponseGenerator.success();
    }


    /**
     * 4. 【查询用户】
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response add(@PathVariable(value = "id") Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            return ResponseGenerator.success(optional.get());
        }
        return ResponseGenerator.fail("not exist this user");
    }


    /**
     * 5. 【删除用户】
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable(value = "id") Long id) {
        userRepository.deleteById(id);
        return ResponseGenerator.success();
    }


}