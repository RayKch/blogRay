package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.data.LoginVo;
import ray.data.param.LoginParamVo;
import ray.repository.LoginDao;

/**
 * Created by BELL on 2016-08-12.
 */
@Slf4j
@Service
public class LoginService {
	@Autowired
	@Setter
	private LoginDao loginDao;

	public LoginVo getVo(LoginParamVo vo) {
		return loginDao.getVo(vo);
	}

	public boolean updateVo(LoginParamVo vo) {
		return loginDao.updateVo(vo) > 0;
	}
}
