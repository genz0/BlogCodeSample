package jp.gr.java_conf.genzo.java.sample.collection;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MapTreeMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NavigableMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i = 1; i < 10; i++) {
			int key = (int) Math.pow(2, i);
			String value = String.format("key is %d", key);
			map.put(key, value);
		}
		// [2, 4, 8, 16, 32, 64, 128, 256, 512]

		int next = 50;
		Map.Entry<Integer, String> entry;
		while ((entry = map.higherEntry(next)) != null) {
			System.out.printf("key=%d value=%s\n", entry.getKey(), entry.getKey());
			next = entry.getKey();
		}
	}

}
