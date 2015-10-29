package logic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.Member;

import org.slf4j.Logger;

import util.Constant;
import bean.LoginUserBean;
import dao.MemberDao;

@RequestScoped
public class LoginService {

	@Inject
	MemberDao dao;

	@Inject
	LoginUserBean loginUser;

	@Inject
	Logger logger;

	/**
	 *
	 * @param empId ログインID
	 * @return データの取得結果
	 */
	public int login(String empId) {
		Member member = dao.find(empId);
		if(member!=null) {
			loginUser.login(member.getId(), member.getName(), member.getMasterMemcat().getId(), member.getMasterMemcat().getName());
			logger.info(member.getId());
			return Constant.SUCCESS;
		} else {
			return Constant.FAILED;
		}
	}

}
