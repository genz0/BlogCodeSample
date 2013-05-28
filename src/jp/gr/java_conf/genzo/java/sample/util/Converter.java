package jp.gr.java_conf.genzo.java.sample.util;

import javax.xml.bind.DatatypeConverter;

public class Converter {

	public static void main(String[] args) {

		// 文字列からUTF8のバイト列に変換
		byte[] data = "1192つくろう鎌倉幕府！！".getBytes();

		// 001122形式の文字列に変換
		String hexText = DatatypeConverter.printHexBinary(data);
		System.out.println("16進文字列　[" + hexText + "]");

		// UTF8のバイト列からstringに変換
		String decText = new String(DatatypeConverter.parseHexBinary(hexText));
		System.out.println("復元結果　[" + decText + "]");
	}

}
