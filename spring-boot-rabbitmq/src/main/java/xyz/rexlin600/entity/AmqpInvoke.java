package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author: hekunlin
 * @date: 2020/1/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmqpInvoke {

    public Method[] methods;

    public Object object;

}