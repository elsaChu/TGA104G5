package tw.com.tibame;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import tw.com.tibame.product.vo.ViewProductVO;

@Configuration
public class RedisConfiguration {
	@Bean
	RedisTemplate<Integer, HashSet<ViewProductVO>> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Integer, HashSet<ViewProductVO>> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
