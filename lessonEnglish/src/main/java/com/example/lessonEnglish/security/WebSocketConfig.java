package com.example.lessonEnglish.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //sử dụng để kích hoạt máy chủ socket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{//cung cấp 1 số phương pháp để kết nối socket
	
	//đăng ký điểm cuối của khách hàng để khách hàng kết nối với máy chủ
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
				.setAllowedOriginPatterns("*")
				.withSockJS(); //sử dụng để kích hoạt các tùy chọn dự phòng cho các trình duyệt không hỗ trợ websocket
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
	} 
	
	
	
	
}
