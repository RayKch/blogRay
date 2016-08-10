package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.CategoryVo;
import ray.data.param.CategoryParamVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CategoryVo> getList(CategoryParamVo vo) {
		return sqlSession.selectList("category.getList", vo);
	}
}
