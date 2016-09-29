package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.StatsVo;
import ray.data.param.StatsParamVo;

/**
 * Created by BELL on 2016-09-29.
 */
@Repository
public class StatsDaoImpl implements StatsDao {
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getStatsCnt(StatsParamVo vo) {
		return sqlSession.selectOne("stats.getStatsCnt", vo);
	}

	@Override
	public int getTodayStatsCnt() {
		return sqlSession.selectOne("stats.getTodayStatsCnt");
	}

	@Override
	public StatsVo getVisitorCnt() {
		return sqlSession.selectOne("stats.getVisitorCnt");
	}

	@Override
	public int insertBufferVo(StatsParamVo vo) {
		return sqlSession.insert("stats.insertBufferVo", vo);
	}

	@Override
	public int insertStatsVo() {
		return sqlSession.insert("stats.insertStatsVo");
	}

	@Override
	public int updateStatsVo() {
		return sqlSession.update("stats.updateStatsVo");
	}

	@Override
	public int deleteBufferVo() {
		return sqlSession.delete("stats.deleteBufferVo");
	}
}
