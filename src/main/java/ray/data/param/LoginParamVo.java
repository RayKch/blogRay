package ray.data.param;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BELL on 2016-08-12.
 */
@Data
@Slf4j
public class LoginParamVo {
	private Integer seq;
	private String id;
	private String password;
	private String loginToken;
	private String lastIp;
}
