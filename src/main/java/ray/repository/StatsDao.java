package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.StatsVo;
import ray.data.param.StatsParamVo;

/**
 * Created by BELL on 2016-09-29.
 */
@Repository
public interface StatsDao {
	public int getStatsCnt(StatsParamVo vo);
	public int getTodayStatsCnt();
	public StatsVo getVisitorCnt();
	public int insertBufferVo(StatsParamVo vo);
	public int insertStatsVo();
	public int updateStatsVo();
	public int deleteBufferVo();
}
