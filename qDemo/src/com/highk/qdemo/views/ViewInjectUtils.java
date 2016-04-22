package com.highk.qdemo.views;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.highk.qdemo.views.annotation.ContentView;
import com.highk.qdemo.views.annotation.ViewInject;

import android.R.integer;
import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ViewInjectUtils {
	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

	public static void injectContentView(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		// 查询类上是否存在ContentView注解
		ContentView contentView = clazz.getAnnotation(ContentView.class);
		if (contentView != null)// 存在
		{
			int contentViewLayoutId = contentView.value();
			try {
				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void injectViews(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Log.i("field", field.toString());
			// 查询类上是否存在ViewInject注解
			ViewInject viewInject = field.getAnnotation(ViewInject.class);
			if (viewInject != null) {
				try {
					// Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
					// int.class);
					// method.setAccessible(true);
					// //findviewbyid 返回的是个view的对象，然后需要把这个域给赋值
					// Object object = method.invoke(activity,
					// viewInject.value());
					// field.setAccessible(true);
					// field.set(activity, object);
					Object object = activity.findViewById(viewInject.value());
					field.setAccessible(true);
					field.set(activity, object);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
