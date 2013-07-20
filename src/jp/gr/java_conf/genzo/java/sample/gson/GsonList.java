package jp.gr.java_conf.genzo.java.sample.gson;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class GsonList {

	public static void main(String[] args) {

		Gson gson = new Gson();
		
		//JSON->POJO(1)
		History his = gson.fromJson(
				new InputStreamReader(GsonObject.class
						.getResourceAsStream("history.json")), History.class);

		System.out.println("---step1---");
		System.out.println(his);

		//JSON->POJO(1)
		History2 his2 = gson.fromJson(
				new InputStreamReader(GsonObject.class
						.getResourceAsStream("history.json")), History2.class);

		System.out.println("---step2---");
		System.out.println(his2);

	}

	// 内部クラスにする場合、staticにしないと面倒が起る
	/**
	 * 解析結果を受け取るクラス(1)
	 */
	static class History {
		// final にすることもできる
		private final int key;
		private final String message;

		// コンストラクタで初期化することで、GSONでもセットできる
		public History() {
			key = 0;
			message = null;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("key=").append(key);
			sb.append(" message=").append(message);

			return new String(sb);
		}
	}

	/**
	 * 解析結果を受け取るクラス(2)
	 */
	static class History2 {
		// JSONのキー名と変数名が異なる場合、@SerializedNameを使う
		@SerializedName("key")
		private int mKey = 0;
		@SerializedName("message")
		private String mMessage = "";

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("key=").append(mKey);
			sb.append(" message=").append(mMessage);

			return new String(sb);
		}
	}
}
