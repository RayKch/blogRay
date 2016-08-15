package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ray.data.CategoryVo;
import ray.data.param.CategoryParamVo;
import ray.repository.CategoryDao;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Slf4j
@Service
public class CategoryService {
	@Autowired
	@Setter
	private CategoryDao categoryDao;

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertVo(CategoryParamVo vo) {
		return categoryDao.insertVo(vo) > 0;
	}

	public List<CategoryVo> getList() {
		return categoryDao.getList();
	}
}
