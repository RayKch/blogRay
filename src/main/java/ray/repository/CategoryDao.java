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
	public List<CategoryVo> getList(CategoryParamVo vo);
	public int getListTotalCount(CategoryParamVo vo);
	public List<CategoryVo> getTitleList();
	public CategoryVo getVo(Integer seq);
	public Integer getMaxOrderNo(CategoryParamVo vo);
	public int insertVo(CategoryParamVo vo);
	public int updateVo(CategoryParamVo vo);
	public int updateOrderNo(CategoryParamVo vo);
	public int deleteVo(CategoryParamVo vo);
}
