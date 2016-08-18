package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;
import ray.repository.BoardDao;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Slf4j
@Service
public class BoardService {
	@Autowired
	@Setter
	private BoardDao boardDao;

	public List<BoardVo> getList(BoardParamVo vo) {
		return boardDao.getList(vo);
	}

	public int getListTotalCount(BoardParamVo vo) {
		return boardDao.getListTotalCount(vo);
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean insertVo(BoardParamVo vo) {
		return boardDao.insertVo(vo) > 0;
	}

	@Transactional(propagation= Propagation.REQUIRED, rollbackFor={Exception.class})
	public boolean updateVo(BoardParamVo vo) {
		return boardDao.updateVo(vo) > 0;
	}

	public boolean deleteVo(int seq) {
		return boardDao.deleteVo(seq) > 0;
	}
}
