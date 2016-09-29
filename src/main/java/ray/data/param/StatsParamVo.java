package ray.data.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BELL on 2016-09-29.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class StatsParamVo extends ParamVo {
	private String typeCode;
	private String ip;
	private Integer boardSeq;
	private int todayCnt;
	private String regDate;

	@Override
	public String getSearch() {
		return search;
	}
}
