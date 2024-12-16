//package com.anti_scam.post.feign;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//@FeignClient(name = "auth-service", url = "http://localhost:8084/api/auth")
//public interface AuthClient {
//    @GetMapping("/validate")
//    String validateToken(@RequestHeader("Authorization") String token);
//}