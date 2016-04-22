package com.highk.qdemo.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 反序列化时的检测类型
 * @author gaofeng
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckType {
	CheckStyle value() default CheckStyle.NONCHECK;
	public enum CheckStyle {
		//只找匹配元素，如果没有对应的元素，则以默认值（基本类型）和null（对象类型）赋值，对于json对象无要求
		NONCHECK,
		//class的属性必须在json中存在，否则无法进行反序列化（json的对象可以比class多）
		CLASS_ROLE,
		//json中的数据必须在class中有对应的属性，否则无法进行反序列化（class的属性可以比json中的对象多）
		JSON_ROLE,
		//必须class的属性与json的key一一对应，否则无法进行反序列化
		MATCH
	}
}
