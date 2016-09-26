package dateuse;

import java.util.Calendar;

public class CalculateTime {
	public static void main(String[] args) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DAY_OF_MONTH, calendar2.get(Calendar.DAY_OF_MONTH) - 1);
		long time = calendar2.getTime().getTime() - calendar1.getTime().getTime();
		System.out.println(time);
		
		long oneDay = 24 * 60 * 60 * 1000;
		long towDay = 2 * oneDay;
		
		System.out.println(oneDay);
		System.out.println(towDay);
	}
}
