package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.data.param.StatsParamVo;
import ray.repository.BoardDao;
import ray.repository.StatsDao;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BELL on 2016-09-29.
 */
@Slf4j
@Service
public class StatsService {
	@Autowired
	@Setter
	private StatsDao statsDao;

	@Autowired
	@Setter
	private BoardDao boardDao;

	public boolean getStatsCnt(StatsParamVo vo) {
		return statsDao.getStatsCnt(vo) > 0;
	}

	public boolean getTodayStatsCnt() {
		return statsDao.getTodayStatsCnt() > 0;
	}

	public boolean insertBufferVo(StatsParamVo vo) {
		return statsDao.insertBufferVo(vo) > 0;
	}

	public boolean insertStatsVo() {
		return statsDao.insertStatsVo() > 0;
	}

	public boolean updateStatsVo() {
		return statsDao.updateStatsVo() > 0;
	}

	public boolean deleteBufferVo() {
		return statsDao.deleteBufferVo() > 0;
	}

	public void viewStatsProcess(Integer seq, HttpServletRequest request) {
		StatsParamVo paramVo = new StatsParamVo();
		paramVo.setTypeCode("view");
		paramVo.setIp(request.getRemoteAddr());
		paramVo.setBoardSeq(seq);

		if(!getStatsCnt(paramVo)) {
			insertBufferVo(paramVo);
			boardDao.updateViewCnt(seq);
		}
	}

	public void todayStatsProcess(HttpServletRequest request) {
		StatsParamVo paramVo = new StatsParamVo();
		paramVo.setTypeCode("today");
		paramVo.setIp(request.getRemoteAddr());

		if(!getStatsCnt(paramVo)) {
			insertBufferVo(paramVo);
			if(getTodayStatsCnt()) {
				updateStatsVo();
			} else {
				insertStatsVo();
			}
		}
	}
}
