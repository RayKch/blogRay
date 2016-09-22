package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.BoardVo;
import ray.data.param.BoardParamVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Repository
public interface BoardDao {
	public List<BoardVo> getList(BoardParamVo vo);
	public int getListTotalCount(BoardParamVo vo);
	public BoardVo getVo(Integer seq);
	public int insertVo(BoardParamVo vo);
	public int updateVo(BoardParamVo vo);
	public int deleteVo(Integer seq);
	public List<BoardVo> getCommentList(BoardParamVo vo);
	public int getCommentCnt(Integer loginSeq);
	public int insertCommentVo(BoardParamVo vo);
	public int deleteCommentVo(BoardParamVo vo);
	public int updateViewCnt(int seq);
}
