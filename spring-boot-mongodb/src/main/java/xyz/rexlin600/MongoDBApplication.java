package xyz.rexlin600;

import com.spring4all.mongodb.EnableMongoPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MongoDBApplication 启动类
 *
 * @author: rexlin600
 * @date: 2020-01-11 00:36:26
 */
@EnableMongoPlus
@SpringBootApplication
public class MongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDBApplication.class, args);
    }

}