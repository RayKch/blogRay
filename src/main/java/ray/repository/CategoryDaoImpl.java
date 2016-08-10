package ray.repository;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Repository
public class CategoryDaoImpl {
	@Setter
	@Autowired
	private SqlSession sqlSession;
}
