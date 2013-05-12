package jp.gr.java_conf.genzo.java.sample.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class SyncExchanger {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new SyncExchanger().start();
	}

	private void start() {

		Exchanger<Integer> exchanger = new Exchanger<Integer>();

		new Task1(exchanger).start();
		new Task2(exchanger).start();
	}

	static class Task1 extends Thread {

		Exchanger<Integer> exchanger;

		public Task1(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			int in = new Random().nextInt(1000);
			int out = 0;

			try {
				// 何か時間がかかる処理
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));

				// データをわたす
				out = exchanger.exchange(in);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Task1 end in=" + in + " out=" + out);

		}
	}

	static class Task2 extends Thread {

		Exchanger<Integer> exchanger;

		public Task2(Exchanger<Integer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			int in = new Random().nextInt(1000);
			int out = 0;

			try {
				// データを待つ
				out = exchanger.exchange(in);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task2 end in=" + in + " out=" + out);
		}
	}
}
