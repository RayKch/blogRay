package ray.data.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ChanPong on 2016-08-11.
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class CategoryParamVo extends ParamVo {
	@Override
	public String getSearch() {
		return "";
	}
}
