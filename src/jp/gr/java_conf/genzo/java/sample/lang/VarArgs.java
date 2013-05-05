package jp.gr.java_conf.genzo.java.sample.lang;

import java.util.Arrays;

public class VarArgs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Stringの場合
		String[] stringArray = Converter.convert("a", "b", "c");
		System.out.println("stringArray = " + Arrays.toString(stringArray));

		// 数値はラッパー型になる。intで処理できないのが残念。。。
		Integer[] intArray = Converter.convert(1, 2, 3);
		System.out.println("intArray = " + Arrays.toString(intArray));
		// オーバーロードすればそれなりにできるが...

		// 配列同士の連結
		System.out.println("Array+Array = "
				+ Arrays.toString(Converter.marge(stringArray, new String[]{"x","y","z"})));

	}

	public static class Converter {
		@SafeVarargs
		static <T> T[] convert(T... args) {
			// テンプレート型指定でキャスト不要
			// 引数をそのまま返すだけ!
			return args;
		}

		@SafeVarargs
		static <T> T[] marge(T[] array, T... args) {
			//同じタイプじゃないと例外になる
			
			// <T>の場合、インスタンスを生成できないのでArrays.copyOfで配列を拡張
			T[] temp = Arrays.copyOf(array, array.length + args.length);
			// 配列をコピー
			System.arraycopy(args, 0, temp, array.length, args.length);
			return temp;
		}
	}

}
