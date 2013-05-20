package jp.gr.java_conf.genzo.java.sample.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InitializeSet {

	// 更新できないSetをメンバ変数として生成する
	private final Set<String> set = Collections.unmodifiableSet(
			new HashSet<String>(
					Arrays.asList(
							"aaa",
							"bbb",
							"ccc",
							"ddd"
					)
				)
			);

	

	private final Set<Integer> set2 = newUmSet(
			1,
			2,
			3,
			4
	);
	
	public void print() {
		System.out.println(set);
		System.out.println(set2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new InitializeSet().print();
		// 出力結果。HashSetなので順不同
		// [aaa, ddd, ccc, bbb]
		// [1, 2, 3, 4]
	}
	
	@SafeVarargs
	static <T> List<T> newUmList(T... args){
		List<T> list = Arrays.asList(args);
		return Collections.unmodifiableList(list);
	}

	@SafeVarargs
	static <T> Set<T> newUmSet(T... args){
		Set<T> set = new HashSet<T>(Arrays.asList(args));
		return Collections.unmodifiableSet(set);
	}

}
