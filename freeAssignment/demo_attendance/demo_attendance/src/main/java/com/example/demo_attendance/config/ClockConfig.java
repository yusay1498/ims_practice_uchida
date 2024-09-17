package com.example.demo_attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfig {
    @Bean
    public Clock clock() {
        // 本番環境では標準のシステムクロックを提供
        return Clock.systemDefaultZone();
    }
}
