package jp.gr.java_conf.genzo.java.sample.thread;

import java.util.Random;

public class CountNoCare {

	static private int totalCount = 0;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		// スレッドのインスタンスを生成する
		Task[] task = new Task[100];
		for (int i = 0; i < task.length; i++) {
			task[i] = new Task(i);
		}

		// スレッドを起動する
		for (int i = 0; i < task.length; i++) {
			task[i].start();
		}

		// スレッドの終了を待ち合わせる
		for (int i = 0; i < task.length; i++) {
			task[i].join();
		}

		// トータルを表示する
		System.out.println("totalCount = " + totalCount);

	}

	static class Task extends Thread {

		// 乱数を生成する
		static Random RANDOM = new Random();

		Task(int num) {
			// スレッド名を指定する
			super("ID" + num);
		}

		@Override
		public void run() {

			try {
				// 乱数を使って、任意の待ち時間を決定する
				int wait = RANDOM.nextInt(1000);
				Thread.sleep(wait);

				// インクリメントする。排他制御していないので、時々カウントアップされない時がある
				totalCount++;
				System.out.println(getName() + " = " + totalCount);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
