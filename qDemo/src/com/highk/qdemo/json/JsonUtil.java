package com.highk.qdemo.json;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.highk.qdemo.json.annotation.JsonAutoDetect;
import com.highk.qdemo.json.annotation.JsonName;
import com.highk.qdemo.json.annotation.JsonAutoDetect.Visibility;
import com.highk.qdemo.json.util.JsonTypeUtil;

public class JsonUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T parseObject(JSONObject jo, Class<T> clazz)
			throws JSONException {
		if (clazz == null || jo == null) {
			return null;
		}
		//首选确认class的类型，如果是map则直接转换
		if (clazz.isAssignableFrom(Map.class)) {
			return (T) jsonTransMap(jo);
		}
		
		T obj = newInstance(clazz);
		JsonAutoDetect jsonAutoDetect = clazz
				.getAnnotation(JsonAutoDetect.class);
		Field[] fields = null;
		if (jsonAutoDetect.fieldVisbility() == Visibility.NONE) {
			return obj;
		} else if (jsonAutoDetect.fieldVisbility() == Visibility.PUBLIC_ONLY) {
			// 公有对象序列化
			fields = clazz.getFields();
		} else {
			// 非公有对象序列化
			fields = clazz.getDeclaredFields();
		}

		for (Field field : fields) {
			Class<?> type = field.getType();
			field.setAccessible(true);
			String name = null;
			if (field.getAnnotation(JsonName.class) != null) {
				name = field.getAnnotation(JsonName.class).value();
			} else {
				name = field.getName();
			}
			if (type.isPrimitive()) {
				// 基本类型
				try {
					if (jo.has(name)) {
						field.set(obj, jo.get(name));
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new JSONException("field set value error");
				}
			} else {
				// 判断是否是java的包装对象
				if (isValueAble(type)) {
					try {
						field.set(obj,
								newInstance(type, jsonGetValue(jo, name)));
					} catch (Exception e) {
						e.printStackTrace();
						throw new JSONException(field.getName()
								+ " field set value error");
					}
				} else {
					// 其他的java对象
					Object object = jsonGetValue(jo, name);
					try {
						
						if (object == null) {
							field.set(obj, null);
						} else if (object.getClass() == type) {
							field.set(obj, object);
						}else if (object instanceof JSONObject) {
							field.set(obj,
									parseObject((JSONObject) object, type));
						} else if (object instanceof String) {
							field.set(
									obj,
									parseObject(
											new JSONObject((String) object),
											type));
						} else {
							throw new JSONException(field.getName()
									+ " field set value error");
						}
					} catch (Exception e) {
						throw new JSONException(field.getName()
								+ " field set value error");
					}
				}
			}
		}
		return obj;
	};

	private static Map<String, Object> jsonTransMap(JSONObject jo) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
	    @SuppressWarnings("unchecked")
		Iterator<String> keysIterator = jo.keys();
	    while (keysIterator.hasNext()) {
			String key = keysIterator.next();
			map.put(key, jo.get(key));
		}
		return map;
	}

	private static Object newInstance(Class<?> type, Object object)
			throws JSONException {
		if (String.class == type) {
			return JsonTypeUtil.transToString(object);
		} else if (Character.class == type) {
			return JsonTypeUtil.transToCharacter(object);
		} else if (BigDecimal.class == type) {
			return JsonTypeUtil.transToBigDecimal(object);
		} else if (Byte.class == type) {
			return JsonTypeUtil.transToByte(object);
		} else if (Integer.class == type) {
			return JsonTypeUtil.transToInteger(object);
		} else if (AtomicInteger.class == type) {
			return JsonTypeUtil.transToAtomicInteger(object);
		} else if (BigInteger.class == type) {
			return JsonTypeUtil.transToBigInteger(object);
		} else if (Double.class == type) {
			return JsonTypeUtil.transToDouble(object);
		} else if (Float.class == type) {
			return JsonTypeUtil.transToFloat(object);
		} else if (Long.class == type) {
			return JsonTypeUtil.transToLong(object);
		}

		throw new JSONException(type.toString() + " can't support");
	}

	private static boolean isValueAble(Class<?> type) {
		return isBoolean(type) || isNumber(type) || isString(type);
	}

	private static boolean isString(Class<?> type) {
		return String.class.isAssignableFrom(type)
				|| Character.class.isAssignableFrom(type);
	}

	private static boolean isNumber(Class<?> type) {
		return Number.class.isAssignableFrom(type);
	}

	private static boolean isBoolean(Class<?> type) {
		return Boolean.class.isAssignableFrom(type);
	}


	private static Object jsonGetValue(JSONObject js, String name) {
		if (js.has(name)) {
			try {
				return js.get(name);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	private static <T> T newInstance(Class<T> clazz) throws JSONException {
		if (clazz == null) {
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new JSONException("class can't instantiate");
		}
	}

}
