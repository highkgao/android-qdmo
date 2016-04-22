package com.example.qdemotest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import com.highk.qdemo.json.JsonUtil;

import android.test.AndroidTestCase;
import android.util.Log;

public class JsonTest extends AndroidTestCase {
	public void testDeserializer()  { 
		try {
			User user = new User();
			user.age = 1;
			user.atomicInteger = new AtomicInteger(1);
			user.bigDecimal = new  BigDecimal(1);
			user.bigInteger = new BigInteger("123456789012345");
			user.character = new Character('X');
			user.double1 = 0.15;
			user.double2 = 0.16;
			user.float1 = 0.17f;
			user.integer = 1234;
			user.long1 = 123456789012345L;
			user.Name = "hello";
			user.annotationTest = new AnnotationTest();
			user.annotationTest.name = "hello";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("age",1);
			jsonObject.put("atomicInteger",new AtomicInteger(1));
			jsonObject.put("bigDecimal",new  BigDecimal(1));
			jsonObject.put("bigInteger",new BigInteger("123456789012345"));
			jsonObject.put("character",new Character('X'));
			jsonObject.put("double1",0.15);
			jsonObject.put("double2",0.16);
			jsonObject.put("float1",0.17f);
			jsonObject.put("integer",1234);
			jsonObject.put("long1",123456789012345L);
			jsonObject.put("Name","hello");
			AnnotationTest annotationTest = new AnnotationTest();
			annotationTest.name = "hello";
			jsonObject.put("annotationTest", annotationTest);
			User user2 = JsonUtil.parseObject(jsonObject, User.class);
			
			Log.i("user1", user.toString());
			Log.i("user2", user2.toString());
			//这里的json对象需要注意annotationTest的tostring不是json格式的，所以需要另外处理
			JSONObject jsonObject3= new JSONObject();
			jsonObject3.put("userName", "hello");
			jsonObject.put("annotationTest", jsonObject3);
			String text = jsonObject.toString();
			Log.i("text", text);
			JSONObject jsonObject2 = new JSONObject(text);
			Log.i("text2", jsonObject2.toString());
			User user3 = JsonUtil.parseObject(jsonObject2, User.class);
			
			Log.i("user3", user3.toString());
			assertTrue(user.toString().equals(user2.toString()));
			assertTrue(user.toString().equals(user3.toString()));
		} catch (Exception e) {
			assertFalse(true);
		}

    }  
	
	public void testAnnotation() throws JSONException {
		AnnotationTest annotationTest = new AnnotationTest();
		annotationTest.name = "hello";
		
		JSONObject jsonObject = new JSONObject();
		//这里通过jsonName的注解，将会把json里面的userName映射为对象的name
		jsonObject.put("userName", "hello");
		//这个类的age属性是私有的，并在类头上有仅进行公有属性的转换，所以转换出来的age，应该是默认的int值0而不是100
		jsonObject.put("age", 100);
		
		AnnotationTest annotationTest2 = JsonUtil.parseObject(jsonObject, AnnotationTest.class);
		Log.i("test2", annotationTest2.toString());
		assertTrue(annotationTest.toString().equals(annotationTest2.toString()));
		
		AnnotationVisibily annotationVisibily = new AnnotationVisibily();
		annotationVisibily.age = 100;
		annotationVisibily.name = "hello";
		
		AnnotationVisibily annotationVisibily2 = JsonUtil.parseObject(jsonObject, AnnotationVisibily.class);
		Log.i("test3", annotationVisibily2.toString());
		Map<String, Object> map = JsonUtil.parseObject(jsonObject, Map.class);
		Log.i("test4", map.toString());
		assertTrue(annotationVisibily.toString().equals(annotationVisibily2.toString()));
	}
      
}
