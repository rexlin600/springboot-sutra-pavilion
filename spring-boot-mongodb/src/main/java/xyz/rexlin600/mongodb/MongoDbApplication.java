package xyz.rexlin600.mongodb;

import com.spring4all.mongodb.EnableMongoPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MongoDBApplication 启动类
 *
 * @author: rexlin600
 * @since: 2020-01-11 00:36:26
 */
@EnableMongoPlus
@SpringBootApplication
public class MongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbApplication.class, args);
    }

}