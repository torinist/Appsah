package util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Dependent
public class Tracer implements Serializable {

	@Inject
	transient Logger logger;

	@Inject
	LoggingUtil lu;


	/*
	 * コンストラクタ用のインタセプタ
	 */
	@AroundConstruct
	public void constructorLog(InvocationContext ic) throws Exception {
		logger.info("******* " + lu.constructorName(ic) + "のインスタンスを作成。引数：" + lu.paramList(ic) + " *******");

		try {
			// コンストラクタ実行
			ic.proceed();
		} finally {
			logger.info("******* " + lu.constructorName(ic) + "のインスタンス作成終了 *******");
		}
	}

	// ポストコンストラクタ用のインタセプタ
	@PostConstruct
	public void postConstructLog(InvocationContext ic) {
		logger.info("******* PostConstruct開始 *******");
	}

	/*
	 * メソッド用のインタセプタ
	 */
	@AroundInvoke
	public Object methodLog(InvocationContext ic) throws Exception {
		logger.info("******* " + lu.className(ic) + "#" +  lu.methodName(ic) + "メソッドを開始。引数：" + lu.paramList(ic) + " *******");
		Object result = null;

		try {
			result = ic.proceed();
			return result;
		} finally {
			logger.info("******* " + lu.className(ic) + "#" +  lu.methodName(ic) + "メソッドを終了 *******");
		}
	}

}
