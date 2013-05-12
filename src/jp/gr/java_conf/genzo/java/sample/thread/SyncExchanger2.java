package jp.gr.java_conf.genzo.java.sample.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class SyncExchanger2 {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new SyncExchanger2().start();
	}

	private void start() {

		Exchanger<ExchangeData> exchanger = new Exchanger<ExchangeData>();

		new Task1(exchanger).start();
		new Task2(exchanger).start();
	}

	static class Task1 extends Thread {

		private final Exchanger<ExchangeData> exchanger;

		public Task1(Exchanger<ExchangeData> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			ExchangeData data = new ExchangeData();

			try {

				do {
					Thread.sleep(TimeUnit.SECONDS.toMillis(6));
					exchanger.exchange(data);

					int result = data.incliment();
					System.out.println("Task1 incliment result=" + result);
				} while (data.isFinish());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task1 end");
		}
	}

	static class Task2 extends Thread {

		private final Exchanger<ExchangeData> exchanger;

		public Task2(Exchanger<ExchangeData> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {

			ExchangeData data = null;

			try {
				do {
					Thread.sleep(TimeUnit.SECONDS.toMillis(3));
					data = exchanger.exchange(data);

					int result = data.incliment();
					System.out.println("Task2 incliment result=" + result);
				} while (data.isFinish());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task2 end");
		}
	}

	static class ExchangeData {
		private int data = 0;

		public int incliment() {
			return (data++);
		}

		public boolean isFinish() {
			return data < 10;
		}
	}

}