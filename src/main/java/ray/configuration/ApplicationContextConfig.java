package ray.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"ray.configuration", "ray.service", "ray.repository", "ray.util.crypt"})
public class ApplicationContextConfig {

}
