package jp.gr.java_conf.genzo.java.sample.lang;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

public class EnumInt {

	static class Status {
		/** 停止. */
		static final int stop = 0;
		/** 一時停止. */
		static final int pause = 1;
		/** 再生. */
		static final int play = 2;
		/** 早送. */
		static final int next = 10;
		/** 巻戻. */
		static final int prev = 11;
		/** 録音. */
		static final int rec = 20;

		/** 再生中のSet. */
		static final private Set<Integer> STAT_PLAY = new HashSet<Integer>();
		static {
			STAT_PLAY.add(pause);
			STAT_PLAY.add(play);
		}

		/** 再生中. */
		static boolean isPlaying(int stat) {
			return STAT_PLAY.contains(stat);
		}

		/** 停止中. */
		static boolean isStoped(int stat) {
			return stat == stop;
		}

		/** 動作中. */
		static boolean isOperating(int stat) {
			return stat == stop;
		}

		/** 値一覧取得. */
		static public int[] values() {
			return new int[] { pause, stop, play, next, prev, rec, };
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

		int[] stats = Status.values();
		int stopped = 0;
		for (int count = 0; count < loop; count++) {
			int idx = count % stats.length;
			int stat = stats[idx];
			if (Status.isStoped(stat)) {
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
			int stat = stats[idx];
			if (Status.isPlaying(stat)) {
				playing++;
			}
		}
		cur = System.nanoTime();
		System.out.println("step2 time=" + fmt.format(cur - start)
				+ " playing=" + playing);
	}

}
