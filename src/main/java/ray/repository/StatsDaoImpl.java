package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	public int insertBufferVo(StatsParamVo vo) {
		return sqlSession.insert("stats.insertBufferVo", vo);
	}

	@Override
	public int updateStatsVo(StatsParamVo vo) {
		return sqlSession.update("stats.updateStatsVo", vo);
	}
}
