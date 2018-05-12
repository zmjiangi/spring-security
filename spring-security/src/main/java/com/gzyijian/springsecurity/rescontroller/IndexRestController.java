package com.gzyijian.springsecurity.rescontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zmjiangi on 2018-5-11.
 */
@RestController
public class IndexRestController {

    @GetMapping("/index")
    public String index() {
        return "access index success";
    }

}