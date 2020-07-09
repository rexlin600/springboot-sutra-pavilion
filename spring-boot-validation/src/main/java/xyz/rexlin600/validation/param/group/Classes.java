package xyz.rexlin600.validation.param.group;

import javax.validation.groups.Default;

/**
 * @author: hekunlin
 * @since: 2020/6/1
 */
public interface Classes extends Default {

    // 如果不继承 Default 则使用 Name Group 时不会校验其余没有划分 Group 的参数！

}