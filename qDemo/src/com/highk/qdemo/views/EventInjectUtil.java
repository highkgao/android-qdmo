package com.highk.qdemo.views;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.highk.qdemo.views.annotation.EventBase;
import android.app.Activity;
import android.view.View;
public class EventInjectUtil {
	public static void eventInject(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		Method[] methods = clazz.getMethods();
		//遍历所有的方法
		for (Method method : methods)
		{
			Annotation[] annotations = method.getAnnotations();
			//拿到方法上的所有的注解
			for (Annotation annotation : annotations)
			{
				Class<? extends Annotation> annotationType = annotation
						.annotationType();
				//拿到注解上的注解
				EventBase eventBaseAnnotation = annotationType
						.getAnnotation(EventBase.class);
				//如果设置为EventBase
				if (eventBaseAnnotation != null)
				{
					//取出设置监听器的名称，监听器的类型，调用的方法名
					String listenerSetter = eventBaseAnnotation
							.listenerSetter();
					Class<?> listenerType = eventBaseAnnotation.listenerType();
					String methodName = eventBaseAnnotation.methodName();

					try
					{
						//拿到Onclick注解中的value方法
						Method aMethod = annotationType
								.getDeclaredMethod("value");
						//取出所有的viewId
						int[] viewIds = (int[]) aMethod
								.invoke(annotation, null);
						//通过InvocationHandler设置代理
						DynamicHandler handler = new DynamicHandler(activity);
						//往map添加方法
						handler.addMethod(methodName, method);
						Object listener = Proxy.newProxyInstance(
								listenerType.getClassLoader(),
								new Class<?>[] { listenerType }, handler);
						//遍历所有的View，设置事件
						for (int viewId : viewIds)
						{
							View view = activity.findViewById(viewId);
							Method setEventListenerMethod = view.getClass()
									.getMethod(listenerSetter, listenerType);
							setEventListenerMethod.invoke(view, listener);
						}

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}

			}
		}
	}
}
