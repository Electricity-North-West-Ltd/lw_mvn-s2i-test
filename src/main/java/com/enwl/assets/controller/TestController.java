package com.enwl.assets.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        var headerNames = request.getHeaderNames();
        var headerAttributes = "";

        while (headerNames.hasMoreElements()) {
            var header = headerNames.nextElement();
            headerAttributes+=", "+header+": "+request.getHeader(header);
        }
        return "Test controller called from "+request.getRemoteAddr()+headerAttributes;
    }
}
