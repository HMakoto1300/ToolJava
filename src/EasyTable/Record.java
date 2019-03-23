package EasyTable;

import java.lang.reflect.Field;

abstract public class Record {
	
	public boolean match(String col,Object value){
		Field[] field = this.getClass().getDeclaredFields();
		for(Field fld : field) {
			if(col.equals(fld.getName())) {
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
	public String toString() {
		String str = "";
		Field[] field = this.getClass().getDeclaredFields();

		for(Field fld : field) {
			try {
				if(str != "") {
					str +=",";
				}
				str += fld.get(this).toString();
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
			
		}
		
		return str;
	}
				
		
	
	

}
