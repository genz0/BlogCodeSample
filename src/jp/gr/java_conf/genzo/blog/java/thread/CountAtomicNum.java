package jp.gr.java_conf.genzo.blog.java.thread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CountAtomicNum {

	static private AtomicInteger totalCount = new AtomicInteger(0);

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
		System.out.println("totalCount = " + totalCount.get());

	}

	/**
	 * 
	 * @author genzo
	 * 
	 */
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

				// インクリメントする。AtomicIntegerなので排他制御しなくてもシリアライズされる
				int result = totalCount.incrementAndGet();
				System.out.println(getName() + " = " + result);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
