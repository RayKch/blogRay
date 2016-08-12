package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.LoginVo;
import ray.data.param.LoginParamVo;

/**
 * Created by BELL on 2016-08-12.
 */
@Repository
public class LoginDaoImpl implements LoginDao {
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public LoginVo getVo(LoginParamVo vo) {
		return sqlSession.selectOne("login.getVo", vo);
	}

	@Override
	public int updateVo(LoginParamVo vo) {
		return sqlSession.update("login.updateVo", vo);
	}
}
