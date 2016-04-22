package com.example.qdemotest;

import com.highk.qdemo.json.annotation.JsonAutoDetect;
import com.highk.qdemo.json.annotation.JsonName;
import com.highk.qdemo.json.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisbility = Visibility.ANY)
public class AnnotationVisibily {
	@JsonName("userName")
	public String name;
	public int age;
	@Override
	public String toString() {
		return "AnnotationVisibily [name=" + name + ", age=" + age + "]";
	}
	
}
