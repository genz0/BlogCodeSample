package jp.gr.java_conf.genzo.java.sample.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class CountReentrantLock {

	// カウント
	private int totalCount = 0;

	public int geTotalCount() {
		return totalCount;
	}

	// 乱数を生成する
	private static final Random RANDOM = new Random();

	// 同実行制御を1で管理
	private static final ReentrantLock LOCK = new ReentrantLock();

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

				// ロックを取得する
				LOCK.lock();

				// インクリメントする。
				totalCount++;
				System.out.println(getName() + " = " + totalCount);


			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				// ロックを開放する
				LOCK.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// インスタンスを生成
		CountReentrantLock semaphore = new CountReentrantLock();

		// スレッドを実行
		semaphore.execute();

		// バリアポイントに達するまで実行されない
		System.out.println("totalCount = " + semaphore.geTotalCount());

	}

}
