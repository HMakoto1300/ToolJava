package EasyTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract public class Record {

	public boolean match(String col, Object value) {
		Field[] field = this.getClass().getDeclaredFields();
		for (Field fld : field) {
			if (col.equals(fld.getName())) {
				try {
					return value.equals(fld.get(this));
				} catch (IllegalArgumentException e) {
					return false;
				} catch (IllegalAccessException e) {
					return false;
				}
			}
		}
		return false;
	}

	public int compareTo(String col, Object value) {
		Field[] field = this.getClass().getDeclaredFields();
		for (Field fld : field) {
			if (col.equals(fld.getName())) {
				try {
					Method m = fld.get(this).getClass().getMethod("compareTo", fld.get(this).getClass());
					return (int) m.invoke(fld.get(this), value);

				} catch (IllegalArgumentException e) {
					return 2;
				} catch (IllegalAccessException e) {
					return 2;
				} catch (NoSuchMethodException e) {
					return 2;
				} catch (SecurityException e) {
					return 2;
				} catch (InvocationTargetException e) {
					return 2;
				}
			}
		}
		return 2;
	}

	public String toString() {
		String str = "";
		Field[] field = this.getClass().getDeclaredFields();

		for (Field fld : field) {
			try {
				if (str != "") {
					str += ",";
				}
				str += fld.get(this).toString();
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}

		}

		return str;
	}

}
