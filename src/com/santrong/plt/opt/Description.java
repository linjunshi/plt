package com.santrong.plt.opt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author weinianjie
 * @date 2014年10月21日
 * @time 上午11:00:44
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	String value() default "";

}
