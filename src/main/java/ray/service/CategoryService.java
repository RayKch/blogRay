package ray.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ray.repository.CategoryDao;

/**
 * Created by ChanPong on 2016-08-10.
 */
@Slf4j
@Service
public class CategoryService {
	@Autowired
	@Setter
	private CategoryDao categoryDao;
}
