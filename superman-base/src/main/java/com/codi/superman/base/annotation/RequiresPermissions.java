package com.codi.superman.base.annotation;

import java.lang.annotation.*;

/**
 * 参考shiro权限简化版
 *
 * @author shi.pengyan
 * @date 2017-01-10 15:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {

    String value();
}
