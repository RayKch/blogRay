package ray.repository;

/**
 * Created by ChanPong on 2016-10-03.
 */

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.FileVo;

@Repository
public class FileDaoImpl implements  FileDao{
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertVo(FileVo vo) {
		return sqlSession.insert("file.insertVo", vo);
	}
}
