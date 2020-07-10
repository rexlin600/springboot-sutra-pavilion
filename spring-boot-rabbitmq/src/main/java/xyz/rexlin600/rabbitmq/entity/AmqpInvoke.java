package xyz.rexlin600.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * Amqp invoke
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmqpInvoke {

	/**
	 * Methods
	 */
	public Method[] methods;

	/**
	 * Object
	 */
	public Object object;

}