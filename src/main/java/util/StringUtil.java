package util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	/**
	 * バイト配列を指定した文字コードで文字列に変換する
	 * @param b バイト配列
	 * @param charaset 文字コード
	 * @return バイト配列を文字列変換した値。bがnullだった場合は空文字が返る
	 * @throws UnsupportedEncodingException サポートされていないエンコード
	 */
	public static String byteToString(byte[] b, String charaset) throws UnsupportedEncodingException {
		String result;
		if(b==null) {
			result = "";
		} else {
			result = new String(b, charaset);
		}

		return result;
	}

	/**
	 * 文字列を指定した文字コードでバイト配列に変換する
	 * @param str 文字列
	 * @param charaset 文字コード
	 * @return 文字列を変換したバイト配列値。strがnullだった場合は空文字がbyte配列に変換される
	 * @throws UnsupportedEncodingException サポートされていないエンコード
	 */
	public static byte[] stringToByte(String str, String charaset) throws UnsupportedEncodingException {
		if(str==null) {
			str = "";
		}
		byte[] result = str.getBytes(charaset);
		return result;
	}
}
