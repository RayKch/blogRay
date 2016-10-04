package ray.repository;

import org.springframework.stereotype.Repository;
import ray.data.FileVo;

import java.util.List;

/**
 * Created by ChanPong on 2016-10-03.
 */
@Repository
public interface FileDao {
	public List<FileVo> getList(Integer seq);
	public int insertVo(FileVo vo);
	public int deleteVo(FileVo vo);
}
