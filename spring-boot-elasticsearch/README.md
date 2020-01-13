# 简介

集成 `ES`，并提过继承 `ElasticsearchRepository` 来实现对 `index` 的操作，从而提供 `rest` API

## 准备

* 依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
```

* 实体类修改

> 使用 @Document

```java
@Document(indexName = "rexlin600-user", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String remark;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", remark=" + remark + "]";
    }
}
```

* Service 实现类

> 可以从下方的实现类看到，对 ES 的 API 调用可以通过 UserEsRepository 就很方便的实现了！

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEsRepository userEsRepository;

    @Override
    public User create(User user) {
        if (null == user) {
            return null;
        }
        System.out.println(userEsRepository);
        return userEsRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        if (null == id) {
            return null;
        }
        Optional<User> optional = userEsRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<User>();
        userEsRepository.findAll().forEach(item -> {
            users.add(item);
        });
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userEsRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userEsRepository.delete(user);
    }
}
```

* API 调用

接着，我们就可以使用 `ES` 的 `API` 进行相关的开发了，详情请参考代码