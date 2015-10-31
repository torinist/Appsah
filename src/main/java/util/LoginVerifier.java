package util;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;

import bean.LoginUserBean;

/**
 * ログイン判定をするクラス
 * @author 1290402
 *
 */
@Dependent
public class LoginVerifier implements Serializable {

	@Inject
	Logger logger;

	@Inject
	LoginUserBean loginUser;

	public Boolean loginVerify() {
		if(loginUser.getUserId()==null) {
			logger.info("ログインしていません。");
			return Constant.NONLOGIN;
		} else {
			logger.info(loginUser.getUserName() + "がログインしてます。");
			return Constant.LOGIN;
		}
	}
}
