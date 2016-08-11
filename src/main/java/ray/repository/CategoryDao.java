package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.CategoryVo;
import ray.data.param.CategoryParamVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Repository
public interface CategoryDao {
	public int insertVo(CategoryParamVo vo);
}
