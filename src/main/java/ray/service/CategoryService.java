package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ray.data.CategoryVo;
import ray.data.param.BoardParamVo;
import ray.data.param.CategoryParamVo;
import ray.repository.BoardDao;
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

	@Autowired
	@Setter
	private BoardDao boardDao;

	public List<CategoryVo> getList(CategoryParamVo vo) {
		return categoryDao.getList(vo);
	}

	public int getListTotalCount(CategoryParamVo vo) {
		return categoryDao.getListTotalCount(vo);
	}

	public CategoryVo getVo(Integer seq) {
		CategoryVo vo = new CategoryVo();
		CategoryVo getVo = categoryDao.getVo(seq);
		if(getVo == null) {
			BoardParamVo paramVo = new BoardParamVo();
			vo.setBoardCount(boardDao.getListTotalCount(paramVo));
		} else {
			vo = getVo;
		}
		return vo;
	}

	public List<CategoryVo> getTitleList() {
		return categoryDao.getTitleList();
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertVo(CategoryParamVo vo) {
		/** 신규 카테고리가 맨뒤로 위치하도록 order_no을 현재 최대치의 + 1을 시켜서 등록한다 */
		vo.setOrderNo(categoryDao.getMaxOrderNo(vo) + 1);
		return categoryDao.insertVo(vo) > 0;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean updateVo(CategoryParamVo vo) {
		return categoryDao.updateVo(vo) > 0;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean updateOrderNo(CategoryParamVo vo) {
		return categoryDao.updateOrderNo(vo) > 0;
	}

	public boolean deleteVo(CategoryParamVo vo) {
		return categoryDao.deleteVo(vo) > 0;
	}
}
