package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Repository
public class BoardDaoImpl implements BoardDao {
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardVo> getList(BoardParamVo vo) {
		return sqlSession.selectList("board.getList", vo);
	}

	@Override
	public int getListTotalCount(BoardParamVo vo) {
		return sqlSession.selectOne("board.getListTotalCount", vo);
	}

	@Override
	public int insertVo(BoardParamVo vo) {
		return sqlSession.insert("board.insertVo", vo);
	}

	@Override
	public int updateVo(BoardParamVo vo) {
		return sqlSession.update("board.updateVo", vo);
	}

	@Override
	public int deleteVo(Integer seq) {
		return sqlSession.delete("board.deleteVo", seq);
	}
}
