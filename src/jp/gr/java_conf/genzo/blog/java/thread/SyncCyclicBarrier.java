package jp.gr.java_conf.genzo.blog.java.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncCyclicBarrier {

	static private AtomicInteger totalCount = new AtomicInteger(0);
	static private CyclicBarrier cyclicBarrier;

	public static void main(String[] args) throws InterruptedException {

		Task[] task = new Task[100];
		// CyclicBarrierを生成する。引数にこれから生成するスレッド数+1を指定する。
		// +1するのは、自分自身もawaitするためです。
		cyclicBarrier = new CyclicBarrier(task.length + 1);

		// スレッドのインスタンスを生成する
		for (int i = 0; i < task.length; i++) {
			task[i] = new Task(i);
		}

		// スレッドを開始する。
		for (int i = 0; i < task.length; i++) {
			task[i].start();
		}

		try {
			// 100のスレッドがawaitを呼び出されるまで待ち状態になる
			cyclicBarrier.await();
			// バリアポイントに達するまで実行されない
			System.out.println("totalCount = " + totalCount);
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

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
				// 即表示される
				System.out.println(getName() + " = " + result);

				// バリアポイントに達するまで待ちになる
				cyclicBarrier.await();

				// バリアポイントに達するまで実行されない
				System.out.println(getName() + " = " + result +  " await");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
