package xyz.rexlin600.rest;

/**
 * Github接口
 *
 * @author: hekunlin
 * @date: 2020/1/3
 */

import lombok.SneakyThrows;
import org.eclipse.egit.github.core.Key;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.PageIterator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.common.apiparam.Response;
import xyz.rexlin600.common.apiparam.ResponseGenerator;
import xyz.rexlin600.config.runner.GRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @menu Github用户API
 */
@RestController
@RequestMapping(value = "/user")
public class UserRest {

    /**
     * 1. 【用户信息】
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/info")
    public Response info(@RequestParam(value = "username", required = false) String username) {
        User user = null;
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            user = GRunner.userService.getUser();
        } else {
            user = GRunner.userService.getUser(username);
        }
        return ResponseGenerator.success(user);
    }


    /**
     * 2. 【新增邮箱】
     *
     * @param email
     * @return
     */
    @SneakyThrows
    @PostMapping("/email")
    public Response addEmail(@RequestParam(value = "email") String email) {
        // resolve space
        if (!StringUtils.isEmpty(email)) {
            email = email.trim();
        }

        GRunner.userService.addEmail(email);
        return ResponseGenerator.success();
    }


    /**
     * 3. 【删除邮箱】
     *
     * @param email
     * @return
     */
    @SneakyThrows
    @DeleteMapping("/email")
    public Response removeEmail(@RequestParam(value = "email") String email) {
        // resolve space
        if (!StringUtils.isEmpty(email)) {
            email = email.trim();
        }

        GRunner.userService.removeEmail(email);
        return ResponseGenerator.success();
    }


    /**
     * 4. 【查询Followers-不分页】
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @GetMapping("/followers")
    public Response followers(@RequestParam(value = "username", required = false) String username) {
        List<User> list = null;
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            list = GRunner.userService.getFollowers(); // 查询自己的 followers
        } else {
            list = GRunner.userService.getFollowers(username); // 查询指定用户的 followers
        }
        return ResponseGenerator.success(list);
    }


    /**
     * 5. 【查询Followers-分页】
     *
     * @param username
     * @param start
     * @param end
     * @return
     */
    @SneakyThrows
    @GetMapping("/page/followers")
    public Response pageFollowers(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "start", defaultValue = "1") Integer start,
                                  @RequestParam(value = "end", defaultValue = "10") Integer end) {
        List<User> list = new ArrayList();
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            PageIterator<User> p1 = GRunner.userService.pageFollowers(start, end);  // 查询自己的 followers
            while (p1.hasNext()) {
                Collection<User> userCollection = p1.next();
                list.addAll(userCollection);
                break;  // 只遍历前面部分
            }
        } else {
            PageIterator<User> p2 = GRunner.userService.pageFollowers(username, start, end); // 查询指定用户的 followers
            while (p2.hasNext()) {
                Collection<User> userCollection = p2.next();
                list.addAll(userCollection);
                break;  // 只遍历前面部分
            }
        }
        return ResponseGenerator.success(list);
    }


    /**
     * 6. 【查询Following-不分页】
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @GetMapping("/following")
    public Response pageFollowers(@RequestParam(value = "username", required = false) String username) {
        List<User> list = new ArrayList();
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            list = GRunner.userService.getFollowing();
        } else {
            list = GRunner.userService.getFollowing(username); // 查询指定用户的 following
        }
        return ResponseGenerator.success(list);
    }


    /**
     * 7. 【查询Following-分页】
     *
     * @param username
     * @param start
     * @param end
     * @return
     */
    @SneakyThrows
    @GetMapping("/page/following")
    public Response pageFollowing(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "start", defaultValue = "1") Integer start,
                                  @RequestParam(value = "end", defaultValue = "10") Integer end) {
        List<User> list = new ArrayList();
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        if (StringUtils.isEmpty(username)) {
            PageIterator<User> p1 = GRunner.userService.pageFollowing(start, end);  // 查询自己的 following
            while (p1.hasNext()) {
                Collection<User> userCollection = p1.next();
                list.addAll(userCollection);
                break;  // 只遍历前面部分
            }
        } else {
            PageIterator<User> p2 = GRunner.userService.pageFollowing(username, start, end); // 查询指定用户的 following
            while (p2.hasNext()) {
                Collection<User> userCollection = p2.next();
                list.addAll(userCollection);
                break;  // 只遍历前面部分
            }
        }
        return ResponseGenerator.success(list);
    }


    /**
     * 8. 【Follow某个用户】
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @PutMapping("/follow")
    public Response follow(@RequestParam(value = "username") String username) {
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        GRunner.userService.follow(username);
        return ResponseGenerator.success();
    }


    /**
     * 9. 【取消Follow某个用户】
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @PutMapping("/unfollow")
    public Response unfollow(@RequestParam(value = "username") String username) {
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        GRunner.userService.unfollow(username);
        return ResponseGenerator.success();
    }


    /**
     * 10. 【判断是否following某个用户】
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @GetMapping("/isFollow")
    public Response isFollow(@RequestParam(value = "username") String username) {
        // resolve space
        if (!StringUtils.isEmpty(username)) {
            username = username.trim();
        }

        boolean following = GRunner.userService.isFollowing(username);
        return ResponseGenerator.success(following);
    }


    /**
     * 11. 【获取所有key】
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/keys")
    public Response keys() {
        List<Key> keys = GRunner.userService.getKeys();
        return ResponseGenerator.success(keys);
    }

    /**
     * 12. 【新建ssh-key】
     *
     * @param key
     * @return
     */
    @SneakyThrows
    @PostMapping("/key")
    public Response createKey(@RequestBody Key key) {
        Key key1 = GRunner.userService.createKey(key);
        return ResponseGenerator.success(key1);
    }

    /**
     * 13. 【删除ssh-key】
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @DeleteMapping("/key/{id}")
    public Response keys(@PathVariable(value = "id") Integer id) {
        GRunner.userService.deleteKey(id);
        return ResponseGenerator.success();
    }


}