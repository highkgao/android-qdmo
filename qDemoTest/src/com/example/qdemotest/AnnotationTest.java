package com.example.qdemotest;

import com.highk.qdemo.json.annotation.JsonAutoDetect;
import com.highk.qdemo.json.annotation.JsonName;
import com.highk.qdemo.json.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisbility = Visibility.PUBLIC_ONLY)
public class AnnotationTest {
	@JsonName("userName")
	public String name;
	private int age;
	@Override
	public String toString() {
		return "AnnotationTest [name=" + name + ", age=" + age + "]";
	}
		
}


