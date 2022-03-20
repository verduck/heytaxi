package com.moca.heytaxi.security;

import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public JwtChannelInterceptor(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (headerAccessor.getCommand() == StompCommand.CONNECT) {
            final String authorizationHeader = headerAccessor.getFirstNativeHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                Long userId = jwtProvider.getUserId(token);
                User user = userService.loadUserById(userId);

                if (jwtProvider.validateToken(token, user)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    headerAccessor.setUser(usernamePasswordAuthenticationToken);
                }
            }
        }
        return message;
    }
}
