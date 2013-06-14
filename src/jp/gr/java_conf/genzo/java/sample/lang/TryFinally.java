package jp.gr.java_conf.genzo.java.sample.lang;

import java.io.IOException;

public class TryFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hoge1 hoge = null;
		try {
			hoge = new Hoge1();
			hoge.execute();
		} finally {
			if (hoge != null) {
				try {
					hoge.close();
				} catch (IOException e) {
					// ほとんどのケースで、ここで例外が出てもどうしようもない。。。
					// 処理的には割り切りでしょうか。
				}
			}
		}

		//Hoge2,Hoge1はAutoCloseableなので、try-with-resources形式にできる
		try (Hoge2 hoge2 = new Hoge2(); Hoge1 hoge1 = new Hoge1();) {
			hoge1.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static class Hoge1 implements AutoCloseable {

		public void execute() {
			System.out.println("Hoge1 execute");
		}

		@Override
		public void close() throws IOException {
			System.out.println("Hoge1 close");

			throw new IOException("Hoge1 close");
		}

	}

	static class Hoge2 implements AutoCloseable {

		public void execute() {
			System.out.println("Hoge2 execute");
		}

		@Override
		public void close() throws IOException {
			System.out.println("Hoge2 close");
		}
	}

}
