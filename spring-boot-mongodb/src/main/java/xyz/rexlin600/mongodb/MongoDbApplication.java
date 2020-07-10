package xyz.rexlin600.mongodb;

import com.spring4all.mongodb.EnableMongoPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mongo db application
 *
 * @author hekunlin
 */
@EnableMongoPlus
@SpringBootApplication
public class MongoDbApplication {

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MongoDbApplication.class, args);
	}

}