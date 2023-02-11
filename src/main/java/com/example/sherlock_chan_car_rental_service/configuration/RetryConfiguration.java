package com.example.sherlock_chan_car_rental_service.configuration;

import com.example.sherlock_chan_car_rental_service.exception.NotFoundException;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryConfiguration {

    @Bean
    public Retry userServiceRetry(){
        RetryConfig retryConfig = RetryConfig.custom()
                .intervalFunction(IntervalFunction.ofExponentialBackoff(4000,2))
                .maxAttempts(5)
                .ignoreExceptions(NotFoundException.class)
                .build();

        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);

        return retryRegistry.retry("userServiceRetry");
    }
}
