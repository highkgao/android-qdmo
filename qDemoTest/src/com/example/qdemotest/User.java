package com.example.qdemotest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

import android.view.FocusFinder;

import com.highk.qdemo.json.annotation.JsonAutoDetect;
import com.highk.qdemo.json.annotation.JsonName;
import com.highk.qdemo.json.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisbility=Visibility.ANY)
public class User {
	
	String Name;
	int age;
	double double2;
	BigInteger bigInteger;
	Character character;
	BigDecimal bigDecimal;
	Integer integer;
	AtomicInteger atomicInteger;
	Double double1;
	Float float1;
	Long long1;
	AnnotationTest annotationTest;
	@Override
	public String toString() {
		return "User [Name=" + Name + ", age=" + age + ", double2=" + double2
				+ ", bigInteger=" + bigInteger + ", character=" + character
				+ ", bigDecimal=" + bigDecimal + ", integer=" + integer
				+ ", atomicInteger=" + atomicInteger + ", double1=" + double1
				+ ", float1=" + float1 + ", long1=" + long1
				+ ", annotationTest=" + annotationTest + "]";
	}
	
	
}
