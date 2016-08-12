package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.LoginVo;
import ray.data.param.LoginParamVo;

/**
 * Created by BELL on 2016-08-12.
 */
@Repository
public interface LoginDao {
	public LoginVo getVo(LoginParamVo vo);
	public int updateVo(LoginParamVo vo);
}
