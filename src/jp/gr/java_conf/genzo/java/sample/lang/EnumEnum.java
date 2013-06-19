package jp.gr.java_conf.genzo.java.sample.lang;

import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnumEnum {

	public enum Status {
		/** 停止. */
		stop(0),
		/** 一時停止. */
		pause(1),
		/** 再生. */
		play(2),
		/** 早送. */
		next(10),
		/** 巻戻. */
		prev(11),
		/** 録音. */
		rec(20);

		/** 値. */
		private int value = 0;

		/** 再生中のSet. */
		static final private Set<Status> STAT_PLAY = EnumSet.of(pause, play);

		/**
		 * コンストラクタ.
		 * 
		 * @param value
		 */
		private Status(int value) {
			this.value = value;
		}

		/** 再生中. */
		public boolean isPlaying() {
			return STAT_PLAY.contains(this);
		}

		/** 停止中. */
		public boolean isStoped() {
			return stop == this;
		}

		/** 動作中. */
		public boolean isOperating() {
			return stop != this;
		}

		/** int → Status 変換 */
		static final private Map<Integer, Status> CONVERT = new HashMap<Integer, Status>();
		static {
			for (Status status : Status.values()) {
				CONVERT.put(status.value, status);
			}
		}

		/**
		 * int → Status 変換.
		 * 
		 * @param data
		 *            数値
		 * @return 該当のStatus。存在しない場合null.
		 */
		static public Status parse(int data) {
			return CONVERT.get(data);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberFormat fmt = NumberFormat.getNumberInstance();
		long start, cur = 0;
		int loop = 1000000;

		// --- step1
		start = System.nanoTime();

		Status[] stats = Status.values();
		int stopped = 0;
		for (int count = 0; count < loop; count++) {
			int idx = count % stats.length;
			Status stat = stats[idx];
			
			if (stat.isStoped()) {
				stopped++;
			}
		}
		cur = System.nanoTime();
		System.out.println("step1 time=" + fmt.format(cur - start)
				+ " stopped=" + stopped);

		// --- step1
		start = System.nanoTime();

		int playing = 0;
		for (int count = 0; count < loop; count++) {
			int idx = count % stats.length;
			Status stat = stats[idx];
			if (stat.isPlaying()) {
				playing++;
			}
		}
		cur = System.nanoTime();
		System.out.println("step2 time=" + fmt.format(cur - start)
				+ " playing=" + playing);

		EnumEnum e = new EnumEnum();
		e.enumTest(Status.play);
		
	}
	
	public boolean enumTest(Status stat){
		return stat.isPlaying();
	}

}
