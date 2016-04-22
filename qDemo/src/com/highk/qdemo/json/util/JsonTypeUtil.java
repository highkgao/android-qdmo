package com.highk.qdemo.json.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONException;

public class JsonTypeUtil {
	public static BigDecimal transToBigDecimal(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof BigDecimal) {
			return (BigDecimal) object;
		} else if (object instanceof BigInteger) {
			return new BigDecimal((BigInteger) object);
		} else {
			String strVal = object.toString();
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}
			return new BigDecimal(strVal);
		}
	}

	public static Object transToByte(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Number) {
			return ((Number) object).byteValue();
		}

		else if (object instanceof String) {
			String strVal = (String) object;
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}
			return Byte.parseByte(strVal);
		} else {
			throw new JSONException(object + " can't trans to Byte");
		}
	}

	public static Object transToString(Object object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	public static Object transToCharacter(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Character) {
			return (Character) object;
		} else if (object instanceof String) {
			String strVal = (String) object;
			if (strVal.length() == 0) {
				return null;
			} else if (strVal.length() != 1) {
				throw new JSONException(object + " can't trans to Character");
			} else {
				return strVal.charAt(0);
			}
		} else {
			throw new JSONException(object + " can't trans to Character");
		}
	}

	public static Object transToInteger(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Integer) {
			return (Integer) object;
		} else if (object instanceof Number) {
			return ((Number) object).intValue();
		} else if (object instanceof String ) {
			String strVal = (String) object;
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			} else {
				return Integer.parseInt(strVal);
			}
		} else {
			throw new JSONException(object + "can't trans to Integer");
		}
	}

	public static Object transToAtomicInteger(Object object)
			throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof AtomicInteger) {
			return (AtomicInteger) object;
		} else if (object instanceof Number) {
			return new AtomicInteger(((Number) object).intValue());
		} else if (object instanceof String) {
			String strVal = (String) object;
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			} else {
				return new AtomicInteger(Integer.parseInt(strVal));
			}
		} else {
			throw new JSONException(object + "can't trans to AtomicInteger");
		}
	}

	public static Object transToBigInteger(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof BigInteger) {
			return (BigInteger) object;
		} else if (object instanceof Number) {
			return BigInteger.valueOf(((Number) object).longValue());
		} else if (object instanceof String) {
			String strVal = object.toString();
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}

			return new BigInteger(strVal);
		} else {
			throw new JSONException(object + "can't trans to BigInteger");
		}
	}

	public static Object transToDouble(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Number) {
			return ((Number) object).doubleValue();
		} else if (object instanceof String) {
			String strVal = object.toString();
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}
			return Double.parseDouble(strVal);
		} else {
			throw new JSONException(object + "can't trans to Double");
		}
	}

	public static Object transToFloat(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Number) {
			return ((Number) object).floatValue();
		} else if (object instanceof String) {
			String strVal = object.toString();
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}
			return Float.parseFloat(strVal);
		} else {
			throw new JSONException(object + "can't trans to Float");
		}
	}

	public static Object transToLong(Object object) throws JSONException {
		if (object == null) {
			return null;
		} else if (object instanceof Number) {
			return ((Number) object).longValue();
		} else if (object instanceof String) {
			String strVal = object.toString();
			if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
				return null;
			}
			return Long.parseLong(strVal);
		} else {
			throw new JSONException(object + "can't trans to Long");
		}
	}

}
