package jp.gr.java_conf.genzo.blog.java.collection;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class QueueDelayQueue {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {

		long[] waits = { 10, 8, 6, 0, 1, 2 };
		String[] tests = { "10", "8", "6", "0", "1", "2" };

		DelayQueue<Task> queue = new DelayQueue<Task>();
		for (int i = 0; i < waits.length; i++) {
			queue.add(new Task(tests[i], waits[i]));
		}

		while (!queue.isEmpty()) {
			Task task = queue.take();
			task.execute();
		}

	}

	static class Task implements Delayed {

		final private long execTime;
		final private String text;

		Task(String text, long wait) {
			this.text = text;
			this.execTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(wait);
		}

		@Override
		public int compareTo(Delayed arg0) {
			long diff = execTime - ((Task) arg0).execTime;
			return (diff > 0) ? 1 : -1;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return execTime - System.nanoTime();
		}

		public void execute() {
			System.out.println(String.format("%tT text=[%s] wait=%d",
					new Date(), text, execTime));
		}

	}
}
