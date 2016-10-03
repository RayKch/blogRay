package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.FileVo;

/**
 * Created by ChanPong on 2016-10-03.
 */
@Repository
public interface FileDao {
	public int insertVo(FileVo vo);
}
