package tw.com.tibame;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import tw.com.tibame.product.vo.ViewProductVO;

@Configuration
public class RedisConfiguration {
	@Bean
	RedisTemplate<Integer, Set<ViewProductVO>> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Integer, Set<ViewProductVO>> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
