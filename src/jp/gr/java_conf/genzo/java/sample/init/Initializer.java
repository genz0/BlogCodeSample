package jp.gr.java_conf.genzo.java.sample.init;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */

/**
 * @author genzo
 * 
 */
public class Initializer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("0000");
		String aaa = StaticInitClass.aaa;
		System.out.println("1111");
		String bbb = StaticInitClass.bbb;
		System.out.println("2222");
		new StaticInitClass();

		new ExtendClass();

	}

	static class BaseStaticInitClass {
		public static String aaa = "aaa";

	}

	static class StaticInitClass extends BaseStaticInitClass {

		public static String bbb = "bbb";

		static {
			System.out.println("StaticInitClass{}1");
		}
		static {
			System.out.println("StaticInitClass{}2");
		}

		StaticInitClass() {
			System.out.println("StaticInitClass()");
		}
	}

	static class BaseClass {

		{
			System.out.println("BaseClass{}1");
		}

		{
			System.out.println("BaseClass{}2");
		}

		public BaseClass() {
			System.out.println("BaseClass()");
		}
	}

	static class ExtendClass extends BaseClass {

		{
			System.out.println("ExtendClass{}1");
		}

		public ExtendClass() {
			System.out.println("ExtendClass()");
		}

		{
			System.out.println("ExtendClass{}2");
		}
	}

	static class SetInitClass {

		final private Collection<Integer> set1;
		{
			Set<Integer> set = new HashSet<Integer>();
			set.add(1);
			set.add(3);
			set.add(5);
			set1 = Collections.unmodifiableCollection(set);
		}

		final private Collection<Integer> set2;
		{
			Set<Integer> set = new HashSet<Integer>();
			set.add(2);
			set.add(4);
			set.add(6);
			set2 = Collections.unmodifiableCollection(set);
		}

	}

}
