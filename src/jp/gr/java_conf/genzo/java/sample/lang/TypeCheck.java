package jp.gr.java_conf.genzo.java.sample.lang;


/**
 * 
 */
public class TypeCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Object baseClass = new BaseClass();
		Object subClass = new SubClass();

		Object baseClassWithSubOthreType = new BaseClassWithSubOthreType();
		Object subClassWithSubOthreType = new SubClassWithSubOthreType();

		// instance of
		System.out.println("baseClass is Object=" + (baseClass instanceof Object));
		System.out.println("baseClass is BaseClass=" + (baseClass instanceof BaseClass));
		System.out.println("baseClass is SubClass=" + (baseClass instanceof SubClass));
		System.out.println("baseClass is BaseOthreType=" + (baseClass instanceof BaseOthreType));
	
		System.out.println("subClass is Object=" + (subClass instanceof Object));
		System.out.println("subClass is BaseClass=" + (subClass instanceof BaseClass));
		System.out.println("subClass is SubClass=" + (subClass instanceof SubClass));
		System.out.println("subClass is BaseOthreType=" + (subClass instanceof BaseOthreType));

		System.out.println("baseClassWithSubOthreType is Object=" + (baseClassWithSubOthreType instanceof Object));
		System.out.println("baseClassWithSubOthreType is BaseClass=" + (baseClassWithSubOthreType instanceof BaseClass));
		System.out.println("baseClassWithSubOthreType is SubClass=" + (baseClassWithSubOthreType instanceof SubClass));
		System.out.println("baseClassWithSubOthreType is BaseOthreType=" + (baseClassWithSubOthreType instanceof BaseOthreType));

		System.out.println("subClassWithSubOthreType is Object=" + (subClassWithSubOthreType instanceof Object));
		System.out.println("subClassWithSubOthreType is BaseClass=" + (subClassWithSubOthreType instanceof BaseClass));
		System.out.println("subClassWithSubOthreType is SubClass=" + (subClassWithSubOthreType instanceof SubClass));
		System.out.println("subClassWithSubOthreType is BaseOthreType=" + (subClassWithSubOthreType instanceof BaseOthreType));

		// isInstance
		System.out.println("-----------");
		// 比較対象のクラスが自クラスの型に対してい代入可能か判定する
		System.out.println("baseClass is Object=" + Object.class.isInstance(baseClass));
		System.out.println("baseClass is BaseClass=" + BaseClass.class.isInstance(baseClass));
		System.out.println("baseClass is SubClass=" + SubClass.class.isInstance(baseClass));
		System.out.println("baseClass is BaseOthreType=" + BaseOthreType.class.isInstance(baseClass));

		System.out.println("subClass is Object=" + Object.class.isInstance(subClass));
		System.out.println("subClass is BaseClass=" + BaseClass.class.isInstance(subClass));
		System.out.println("subClass is SubClass=" + SubClass.class.isInstance(subClass));
		System.out.println("subClass is BaseOthreType=" + BaseOthreType.class.isInstance(subClass));

		System.out.println("baseClassWithSubOthreType is Object=" + (Object.class.isInstance(baseClassWithSubOthreType)));
		System.out.println("baseClassWithSubOthreType is BaseClass=" + (BaseClass.class.isInstance(baseClassWithSubOthreType)));
		System.out.println("baseClassWithSubOthreType is SubClass=" + (SubClass.class.isInstance(baseClassWithSubOthreType)));
		System.out.println("baseClassWithSubOthreType is BaseOthreType=" + BaseOthreType.class.isInstance(baseClassWithSubOthreType));

		System.out.println("subClassWithSubOthreType is Object=" + Object.class.isInstance(subClassWithSubOthreType));
		System.out.println("subClassWithSubOthreType is BaseClass=" + BaseClass.class.isInstance(subClassWithSubOthreType));
		System.out.println("subClassWithSubOthreType is SubClass=" + SubClass.class.isInstance(subClassWithSubOthreType));
		System.out.println("subClassWithSubOthreType is BaseOthreType=" + BaseOthreType.class.isInstance(subClassWithSubOthreType));
		
		// isAssignableFrom
		System.out.println("-----------");
		// 比較対象のクラスが自クラスのサブクラスか判定する
		System.out.println("baseClass is Object=" + Object.class.isAssignableFrom(baseClass.getClass()));
		System.out.println("baseClass is BaseClass=" + BaseClass.class.isAssignableFrom(baseClass.getClass()));
		System.out.println("baseClass is SubClass=" + SubClass.class.isAssignableFrom(baseClass.getClass()));
		System.out.println("baseClass is BaseOthreType=" + BaseOthreType.class.isAssignableFrom(baseClass.getClass()));

		System.out.println("subClass is Object=" + Object.class.isAssignableFrom(subClass.getClass()));
		System.out.println("subClass is BaseClass=" + BaseClass.class.isAssignableFrom(subClass.getClass()));
		System.out.println("subClass is SubClass=" + SubClass.class.isAssignableFrom(subClass.getClass()));
		System.out.println("subClass is BaseOthreType=" + BaseOthreType.class.isAssignableFrom(subClass.getClass()));

		System.out.println("baseClassWithSubOthreType is Object=" + (Object.class.isAssignableFrom(baseClassWithSubOthreType.getClass())));
		System.out.println("baseClassWithSubOthreType is BaseClass=" + (BaseClass.class.isAssignableFrom(baseClassWithSubOthreType.getClass())));
		System.out.println("baseClassWithSubOthreType is SubClass=" + (SubClass.class.isAssignableFrom(baseClassWithSubOthreType.getClass())));
		System.out.println("baseClassWithSubOthreType is BaseOthreType=" + BaseOthreType.class.isAssignableFrom(baseClassWithSubOthreType.getClass()));

		System.out.println("subClassWithSubOthreType is Object=" + Object.class.isAssignableFrom(subClassWithSubOthreType.getClass()));
		System.out.println("subClassWithSubOthreType is BaseClass=" + BaseClass.class.isAssignableFrom(subClassWithSubOthreType.getClass()));
		System.out.println("subClassWithSubOthreType is SubClass=" + SubClass.class.isAssignableFrom(subClassWithSubOthreType.getClass()));
		System.out.println("subClassWithSubOthreType is BaseOthreType=" + BaseOthreType.class.isAssignableFrom(subClassWithSubOthreType.getClass()));


		// プリミティブ
		System.out.println("Integer isInstance int = " + int.class.isInstance(new Integer(1)));
		System.out.println("int isInstance Integer = " + Integer.class.isInstance(1));

		System.out.println("Integer isAssignableFrom int = " + int.class.isAssignableFrom(Integer.class));
		System.out.println("int isAssignableFrom Integer = " + Integer.class.isAssignableFrom(int.class));

	}

	// BaseClass
	static class BaseClass {

	}

	// SubClass
	static class SubClass extends BaseClass {

	}

	// BaseClass + SubOthreType
	static class BaseClassWithSubOthreType implements SubOthreType {

	}

	// SubClass + SubOthreType
	static class SubClassWithSubOthreType extends SubClass implements SubOthreType {

	}

	// BaseOthreType
	interface BaseOthreType {

	}

	// SubOthreType
	interface SubOthreType extends BaseOthreType {

	}

}
