package jp.gr.java_conf.genzo.java.sample.gson;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonMap {

	public static void main(String[] args) {

		Gson gson = new Gson();

		// Map<String, History>.classと書けないので、TypeTokenを使ってTypeを取り出す
		Type type = new TypeToken<Map<String, History>>() {}.getType();

		// JSON->Map<String,POJO>
		Map<String, History> his = gson.fromJson(new InputStreamReader(
				GsonMap.class.getResourceAsStream("history_map.json")), type);

		System.out.println(his);

	}

	/**
	 * 解析結果を受け取るクラス
	 */
	static class History {
		private int key;
		private String message;

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append("key=").append(key);
			sb.append(" message=").append(message);
			sb.append("]");

			return new String(sb);
		}
	}
}
