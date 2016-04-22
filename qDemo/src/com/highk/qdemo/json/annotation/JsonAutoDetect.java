package com.highk.qdemo.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {
	Visibility fieldVisbility() default Visibility.ANY;
	public enum Visibility {
		ANY,
		NONE,
		PUBLIC_ONLY
	}
}
