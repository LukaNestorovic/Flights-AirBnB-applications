package com.example.suites.client;

import com.example.suites.model.grpcdto.UserUsernameDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", path = "/v1/user")
public interface UserServiceClient {
    @GetMapping("/username/{username}")
    @CircuitBreaker(name = "getUserByUsernameCircuitBreaker", fallbackMethod = "getUserFallback")
    ResponseEntity<UserUsernameDto> getUserByUsername(@PathVariable(value="username") String username);

    default ResponseEntity<UserUsernameDto> getUserFallback(String username, Exception exception){
        return ResponseEntity.ok(new UserUsernameDto("default-id", "default-username"));
    }
}
