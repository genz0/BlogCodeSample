package jp.gr.java_conf.genzo.java.sample.gson;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class GsonEnum {

	public static void main(String[] args) {
		Gson gson = new Gson();

		// タイプを取り出す(List)
		Type type = new TypeToken<List<Signal>>() {}.getType();

		// JSON->List<Signal>
		List<Signal> signal = gson.fromJson(new InputStreamReader(
				GsonMap.class.getResourceAsStream("signal2.json")), type);

		System.out.println("---step1---");
		System.out.println(signal);

		// JSON->List<Signal>
		// #1 TypeAdapterを設定したGsonを生成する
		Gson gson2 = new GsonBuilder().registerTypeAdapter(SignalStat.class,
				new SignalStatDeserializer()).create();
		List<Signal> signal2 = gson2.fromJson(new InputStreamReader(
				GsonMap.class.getResourceAsStream("signal3.json")), type);

		System.out.println("---step2---");
		System.out.println(signal2);
	}

	/**
	 * 解析結果を受け取るクラス
	 */
	static class Signal {
		private SignalStat stat;
		private String message;
		private SignalStat next;

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append("stat=").append(stat);
			sb.append(" message=").append(message);
			sb.append(" next=").append(next);
			sb.append("]");

			return new String(sb);
		}
	}

	/**
	 * ステータスの表現.
	 */
	enum SignalStat {
		Red, Yellow, Blue;

		// #2 変換用のMAP(int -> SignalStat)
		static Map<Integer, SignalStat> CONVERTER = new HashMap<Integer, SignalStat>();
		static {
			for (SignalStat stat : SignalStat.values()) {
				CONVERTER.put(stat.ordinal(), stat);
			}
		}
	}

	/**
	 * SignalStat用Deserializer.
	 */
	static private class SignalStatDeserializer implements
			JsonDeserializer<SignalStat> {

		@Override
		public SignalStat deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			// #3 int -> SignalStat
			return SignalStat.CONVERTER.get(json.getAsInt());
		}
	}
}
