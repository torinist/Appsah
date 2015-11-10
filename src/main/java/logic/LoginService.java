package logic;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.Member;

import org.slf4j.Logger;

import dao.MemberDao;

@Dependent
public class LoginService implements Serializable {

	@Inject
	MemberDao dao;

	@Inject
	Logger logger;

	/**
	 *
	 * @param empId ユーザID
	 * @return ログインユーザのオブジェクト
	 * @throws Exception empIdに該当するユーザが見つからなかった場合に発生する
	 */
	public Member login(String empId)  throws Exception {
		Member member = dao.find(empId);
		if(member!=null) {
			return member;
		} else {
			throw new Exception("該当するユーザが見つかりませんでした。");
		}
	}

}
