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
	public BoardVo getVo(Integer seq) {
		return sqlSession.selectOne("board.getVo", seq);
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

	@Override
	public List<BoardVo> getCommentList(BoardParamVo vo) {
		return sqlSession.selectList("board.getCommentList", vo);
	}

	@Override
	public int getCommentCnt(Integer loginSeq) {
		return sqlSession.selectOne("board.getCommentCnt", loginSeq);
	}

	@Override
	public int insertCommentVo(BoardParamVo vo) {
		return sqlSession.insert("board.insertCommentVo", vo);
	}

	@Override
	public int deleteCommentVo(BoardParamVo vo) {
		return sqlSession.delete("board.deleteCommentVo", vo);
	}
}
