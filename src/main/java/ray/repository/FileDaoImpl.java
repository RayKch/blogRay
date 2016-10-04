package ray.repository;

/**
 * Created by ChanPong on 2016-10-03.
 */

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ray.data.FileVo;

import java.util.List;

@Repository
public class FileDaoImpl implements  FileDao{
	@Setter
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<FileVo> getList(Integer seq) {
		return sqlSession.selectList("file.getList", seq);
	}

	@Override
	public int insertVo(FileVo vo) {
		return sqlSession.insert("file.insertVo", vo);
	}

	@Override
	public int deleteVo(FileVo vo) {
		return sqlSession.delete("file.deleteVo", vo);
	}
}
