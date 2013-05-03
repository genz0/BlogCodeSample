package jp.gr.java_conf.genzo.blog.java.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncExecutorService {

	static private AtomicInteger totalCount = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		// Callableを100件生成するし、コレクションに格納する
		Collection<Callable<Void>> tasks = new ArrayList<Callable<Void>>();
		for (int i = 0; i < 100; i++) {
			tasks.add(new Task(i));
		}

		// ExecutorServiceを生成する。CPUのスペックなど気にせずスレッド数分生成する
		ExecutorService service = Executors.newFixedThreadPool(tasks.size());
		// 実行開始
		service.invokeAll(tasks);
		// ExecutorServiceをシャットダウンする
		service.shutdown();
		// スレッドがすべて終了するか、15秒のタイムアウトのいずれかを待つ
		service.awaitTermination(15, TimeUnit.SECONDS);
		// 結果を表示する
		System.out.println("totalCount = " + totalCount);
	}

	/**
	 * Runnableでも実行できるが、
	 * ExecutorServiceで実行するならCallableのほうが楽。
	 *
	 */
	static class Task implements Callable<Void>{

		String name;
		// 乱数を生成する
		Random RANDOM = new Random();

		Task(int num) {
			// スレッド名を保持する
			name = "ID" + num;
		}

		@Override
		public Void call() throws Exception {
			try {
				// 乱数を使って、任意の待ち時間を決定する
				int wait = RANDOM.nextInt(1000);
				Thread.sleep(wait);

				// インクリメントする。
				int result = totalCount.incrementAndGet();
				System.out.println(name + " = " + result);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
