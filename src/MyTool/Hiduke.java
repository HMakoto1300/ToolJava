package MyTool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Hiduke {
	private Date hiduke;
	public Hiduke (Object o) {

		Class cls = o.getClass();
		String n = cls.getName();
		
		  if(n.equals("java.lang.String")) {
			  String str = (String)o;
			  setHiduke(str);
		  }else if(n.equals("java.util.Date")) {
			  Date date = (Date) o;
			  setHiduke(date);
		  }else {
			  
		  }
	}	
	public void setHiduke(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			this.hiduke = truncateTime(sdf.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setHiduke(Date date) {
		this.hiduke=truncateTime(date);
	}
	

	public Date getHiduke() {
		return this.hiduke;
	}
	
	public void show() {
		System.out.printf("%s",this.hiduke);
	}
	
	public void addDay(int i) {
		this.hiduke=addDay(hiduke,i);
	}
	
	public void addBuDay(int i) {
		this.hiduke=addBuDay(hiduke,i);
	}

	public boolean equals(Hiduke hiduke) {
		if(getHiduke().equals(hiduke.getHiduke())) {
			return true;
		}else {
			return false;
		}
	}
	
	static private Date truncateTime(Date d) {
	    Instant instant = d.toInstant();
	    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("JST", ZoneId.SHORT_IDS));
	    ZonedDateTime truncated = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
	    return Date.from(truncated.toInstant());
	}

	static private Date addDay(Date date,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
        
        // “ú‚ğ‰ÁZ‚·‚é
        calendar.add(Calendar.DATE, i);
        
        // CalendarŒ^‚Ì“ú‚ğDateŒ^‚É–ß‚·
        return calendar.getTime();
	}
	static private Date addBuDay(Date date,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
        if(i==0) {
        	return date;
        }
     	
		int sign = i / Math.abs(i);
		// “ú‚ğ‰ÁZ‚·‚é
		while(i!=0) {
			
			calendar.add(Calendar.DATE, sign);
			if(isDay(calendar.getTime())) {
				i=i-sign;
			}
		}
        
        // CalendarŒ^‚Ì“ú‚ğDateŒ^‚É–ß‚·
        return calendar.getTime();

        
	}

	
	static private boolean isDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
			// “y“ú‚©H
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
		return false;
		}
		// ‘–¯‚Ìj“ú‚©H still donot implimentitaion
		//if (JapaneseHolidayUtils.isHoliday(cal)) {
		//return false;
		//}
		
		return true;
		}	
	
	
}
