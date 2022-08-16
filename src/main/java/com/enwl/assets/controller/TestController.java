package com.enwl.assets.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController {

    private final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        var headerAttributes = "";
        for (var header: IP_HEADER_CANDIDATES) {
            var ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                var ip = ipList.split(",")[0];
                headerAttributes+=", "+header+": "+ip;
            }
        }
        return "Test controller called from "+request.getRemoteAddr()+headerAttributes;
    }
}
