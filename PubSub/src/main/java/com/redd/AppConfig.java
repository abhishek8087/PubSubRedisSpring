package com.redd;


import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;


import com.redd.RedisMessageListener;
import com.redd.RedisPublisherImpl;

@Configuration
@EnableScheduling
public class AppConfig {
	
	String prGlob;
	String CHAT_NAME;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new RedisMessageListener() );
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        
        
		System.out.println("Please enter chatroom name (1 word only):" );
		Scanner sc1 = new Scanner(System.in);
		prGlob=sc1.next();
		PatternTopic pr = new PatternTopic(prGlob);
		
		//RedisMessageListener.pattern1=prGlobal;
		Scanner sc = new Scanner(System.in);
		System.out.println("Set your chatroom name:" );
		CHAT_NAME=sc.next();
		System.out.println("Keep entering text in console... Have fun !" );
        container.setConnectionFactory( jedisConnectionFactory() );
        container.addMessageListener( messageListener(), pr );

        return container;
    }

    @Bean
    RedisPublisherImpl redisPublisher() {
        return new RedisPublisherImpl( redisTemplate(), topic(), CHAT_NAME );
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic( prGlob );
    }
}