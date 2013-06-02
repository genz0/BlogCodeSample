package jp.gr.java_conf.genzo.java.sample.thread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CountAtomicNum {

	// カウント
	private AtomicInteger totalCount = new AtomicInteger(0);

	public int geTotalCount() {
		return totalCount.get();
	}

	// 乱数を生成する
	private static final Random RANDOM = new Random();

	public void execute() throws InterruptedException {
		Task[] task = new Task[100];
		// スレッドのインスタンスを生成する
		for (int i = 0; i < task.length; i++) {
			task[i] = new Task(i);
		}

		// スレッドを開始する。
		for (int i = 0; i < task.length; i++) {
			task[i].start();
		}

		// 終了待機。
		for (int i = 0; i < task.length; i++) {
			task[i].join();
		}
	}

	class Task extends Thread {

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

				// インクリメントする。
				int result = totalCount.incrementAndGet();
				System.out.println(getName() + " = " + result);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// インスタンスを生成
		CountNoCare semaphore = new CountNoCare();

		// スレッドを実行
		semaphore.execute();

		// バリアポイントに達するまで実行されない
		System.out.println("totalCount = " + semaphore.geTotalCount());

	}

}
