package com.niit.uritsolution.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.uritsolution.model.Responce;

@RestController
public class TestController {

    @RequestMapping(value = "/greeting")
    public Responce index() {
    	Responce responce = new Responce();
    	responce.setResponceCode(1);
    	responce.setReposneMessge("Hello world");
        return responce;
    }

}
