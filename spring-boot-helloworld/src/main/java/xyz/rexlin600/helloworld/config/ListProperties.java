package xyz.rexlin600.helloworld.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.rexlin600.helloworld.entity.AliApp;

import java.util.List;

/**
 * List properties
 *
 * @author hekunlin
 */
@ToString
@Data
@Component
@ConfigurationProperties(prefix = "rexlin600.list")
public class ListProperties {

	/**
	 * List
	 */
	private List<String> list;

	/**
	 * Integer list
	 */
	private List<Integer> integerList;

	/**
	 * Double list
	 */
	private List<Double> doubleList;

	/**
	 * Ali app list
	 */
	private List<AliApp> aliAppList;

}