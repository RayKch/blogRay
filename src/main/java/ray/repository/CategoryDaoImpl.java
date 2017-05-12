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

	@Override
	public int getListTotalCount(CategoryParamVo vo) {
		return sqlSession.selectOne("category.getListTotalCount", vo);
	}

	@Override
	public List<CategoryVo> getTitleList() {
		return sqlSession.selectList("category.getTitleList");
	}

	@Override
	public CategoryVo getVo(Integer seq) {
		return sqlSession.selectOne("category.getVo", seq);
	}

	@Override
	public Integer getMaxOrderNo() {
		return sqlSession.selectOne("category.getMaxOrderNo");
	}

	@Override
	public int insertVo(CategoryParamVo vo) {
		return sqlSession.insert("category.insertVo", vo);
	}

	@Override
	public int updateVo(CategoryParamVo vo) {
		return sqlSession.update("category.updateVo", vo);
	}

	@Override
	public int updateOrderNo(CategoryParamVo vo) {
		return sqlSession.update("category.updateOrderNo", vo);
	}

	@Override
	public int deleteVo(Integer seq) {
		return sqlSession.delete("category.deleteVo", seq);
	}
}
