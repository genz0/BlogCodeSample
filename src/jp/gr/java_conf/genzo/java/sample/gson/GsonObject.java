package jp.gr.java_conf.genzo.java.sample.gson;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonObject {

	public static void main(String[] args) {

		Gson gson = new Gson();

		// JSON->JsonObject(Map)
		JsonObject his = gson.fromJson(new InputStreamReader(GsonList.class
						.getResourceAsStream("history_map.json")), JsonObject.class);

		System.out.println("---step1 Map---");
		System.out.println(his);

		// JSON->JsonArray(List)
		JsonArray his2 = gson.fromJson(new InputStreamReader(GsonList.class
						.getResourceAsStream("history_list.json")), JsonArray.class);

		System.out.println("---step2 List---");
		System.out.println(his2);

		// JSON->JsonObject(POJO)
		JsonObject his3 = gson.fromJson(new InputStreamReader(GsonList.class
						.getResourceAsStream("history.json")), JsonObject.class);
		System.out.println("---step3 Object---");
		System.out.println(his3);
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
