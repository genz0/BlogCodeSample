package jp.gr.java_conf.genzo.java.sample.lang;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class NumberConv {

	public static void main(String[] args) {

		Object d = Double.valueOf(3.5d);
		System.out.println("3.5d=" + toInt(d));

		Object f = Float.valueOf(2.5f);
		System.out.println("2.5f=" + toInt(f));

		Object l = Long.valueOf(20l);
		System.out.println("20l=" + toInt(l));

		Object b = Byte.valueOf((byte) 20);
		System.out.println("20b=" + toInt(b));

		Object s = Short.valueOf((short) 20);
		System.out.println("20s=" + toInt(s));

		Object bd = new BigDecimal(4.5);
		System.out.println("4.5bd=" + toInt(bd));

		Object al = new AtomicLong(21);
		System.out.println("21al=" + toInt(al));

	}

	/**
	 * int変換.
	 * 
	 * 数値ラッパーをintに変換する。
	 * 
	 * @param obj
	 * @return 変換した数値
	 */
	public static int toInt(Object obj) {
		if (!(obj instanceof Number)) {
			throw new IllegalArgumentException("not number!!");
		}
		return ((Number) obj).intValue();
	}
}
