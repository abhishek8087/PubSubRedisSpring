package com.redd;



import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
public class RedisMessageListener implements MessageListener {
	
	
    @Override
    public void onMessage( final Message message, final byte[] pattern ) {
    	
        System.out.println( message.toString() );
    }
}
