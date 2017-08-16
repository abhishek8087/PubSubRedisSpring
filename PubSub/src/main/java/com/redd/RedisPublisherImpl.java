package com.redd;


import java.util.Scanner;
//import java.util.concurrent.atomic.AtomicLong;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.scheduling.annotation.Scheduled;

public class RedisPublisherImpl {
	
	public static PatternTopic prGlobal;
	String prGlob;
	private String CHAT_NAME;
    private final RedisTemplate< String, Object > template;
    private final ChannelTopic topic; 
    //private final AtomicLong counter = new AtomicLong( 0 );
    
    
 

    public RedisPublisherImpl( final RedisTemplate< String, Object > template, 
            final ChannelTopic topic, String CHAT_NAME) {
        this.template = template;
        this.topic = topic;
        this.CHAT_NAME=CHAT_NAME;
    }

    @Scheduled( fixedDelay = 10 )
    public void publish() {
        
    	
    	
    	Scanner sc = new Scanner(System.in);
        
        template.convertAndSend( topic.getTopic(),CHAT_NAME +":" + sc.nextLine());
        		 
        	
        
 }
}
