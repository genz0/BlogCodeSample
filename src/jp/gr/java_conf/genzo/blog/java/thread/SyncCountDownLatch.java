package jp.gr.java_conf.genzo.blog.java.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncCountDownLatch {

	static private AtomicInteger totalCount = new AtomicInteger(0);
	static private CountDownLatch countDownLatch;

	public static void main(String[] args) throws InterruptedException {

		Task[] task = new Task[100];
		// CountDownLatchを生成する。引数にこれから生成するスレッド数を指定する
		countDownLatch = new CountDownLatch(task.length);

		// スレッドのインスタンスを生成する
		for (int i = 0; i < task.length; i++) {
			task[i] = new Task(i);
		}

		// スレッドを開始する。
		for (int i = 0; i < task.length; i++) {
			task[i].start();
		}

		// 終わるまで待つ
		countDownLatch.await();
		// バリアポイントに達するまで実行されない
		System.out.println("totalCount = " + totalCount);

	}

	static class Task extends Thread {

		// 乱数を生成する
		Random RANDOM = new Random();

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
				System.out.println(getName() + " = " + result + " await");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// ラッチをカウントダウンする。
				countDownLatch.countDown();
			}
		}
	}

}
