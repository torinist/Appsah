package util;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.enterprise.context.Dependent;
import javax.interceptor.InvocationContext;

/*
 * ログ情報を集めるユーティリティクラス
 */
@Dependent
public class LoggingUtil implements Serializable {

	// クラス名を返す
	public String className(InvocationContext ic) {
		Method method = ic.getMethod();
		Class decClass = method.getDeclaringClass();
		return decClass.getName();
	}

	// メソッド名を返す
	public String methodName(InvocationContext ic) {
		Method method = ic.getMethod();
		return method.getName();
	}

	// 引数を返す
	public String paramList(InvocationContext ic) {
		Object[] params = ic.getParameters();
		return Arrays.toString(params);
	}

	// コンストラクタ名を返す
	public String constructorName(InvocationContext ic) {
		Constructor<?> con = ic.getConstructor();
		return con.getName();
	}
}
