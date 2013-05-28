package jp.gr.java_conf.genzo.java.sample.util;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// try-with-resourcesをサポートするためにjava.io.Closeableをインプリメント
public class StreamTransmitter implements Closeable {

	final private InputStream input;
	final private OutputStream output;
	final int bufSize;

	public StreamTransmitter(InputStream is, OutputStream os) {
		input = is;
		output = os;
		bufSize = 2048;
	}

	public void transfer() throws IOException {
		byte[] buf = new byte[bufSize];

		int read;
		while ((read = input.read(buf)) != -1) {
			output.write(buf, 0, read);
		}
		output.flush();
	}

	@Override
	public void close() throws IOException {

		// 内包するStreamもCloseableなのでtry-with-resourcesでOK
		try (InputStream in = input; OutputStream out = output;) {
		}
	}

	public static void main(String[] args) {

		String in = "/temp/LICENSE.txt";
		String out = "/temp/LICENSE_OUT.txt";

		try (StreamTransmitter st = new StreamTransmitter(new FileInputStream(
				in), new FileOutputStream(out))) {

			st.transfer();

			// try-with-resourcesなので、明示しなくてもOK
			st.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
