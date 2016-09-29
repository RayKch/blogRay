package ray.component.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ray.service.StatsService;

import java.util.*;

/**
 * Created by ChanPong on 2016-09-30.
 */
@Slf4j
@Component
public class StatsManageBatch {
	@Autowired
	private StatsService statsService;

	//매일 00시(밤12시)
	@Scheduled(cron="0 0 0 * * *")
	public void deleteBuffer() throws Exception {
		log.info("############# Buffer Delete Start #############");

		if(!statsService.deleteBufferVo()) {
			log.info("Buffer Delete Fail");
		}

		log.info("############# Buffer Delete End #############");
	}
}