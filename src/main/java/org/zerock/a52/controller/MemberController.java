package org.zerock.a52.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    
    @PreAuthorize("permitAll")
    @GetMapping("/signup")
    public void signup(){

        log.info("GET | signup");
    }

    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mypage")
    public void mypage(){

        log.info("GET | mypage");
    }

    @PreAuthorize("permitAll")
    @GetMapping("/signin")
    public void signin(){
        log.info("GET | signin");
    }
}
