package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.data.param.StatsParamVo;
import ray.repository.StatsDao;

/**
 * Created by BELL on 2016-09-29.
 */
@Slf4j
@Service
public class StatsService {
	@Autowired
	@Setter
	private StatsDao statsDao;

	public boolean getStatsCnt(StatsParamVo vo) {
		return statsDao.getStatsCnt(vo) > 0;
	}

	public boolean insertBufferVo(StatsParamVo vo) {
		return statsDao.insertBufferVo(vo) > 0;
	}

	public boolean updateStatsVo(StatsParamVo vo) {
		return statsDao.updateStatsVo(vo) > 0;
	}
}
