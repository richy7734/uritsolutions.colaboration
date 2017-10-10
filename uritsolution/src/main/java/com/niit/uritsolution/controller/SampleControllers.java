package com.niit.uritsolution.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleControllers {

	@RequestMapping("/get/sample/data")
	public ResponseEntity<String> sendData(@RequestBody String data){
		System.out.println(data);
		return new ResponseEntity<String>("Vimal Raj",HttpStatus.OK);
	}
	
}
