package org.gap.cycleshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/sayhello")
	public String hello(@RequestParam(value = "name", defaultValue = "world")String name) {
		return "hello " + name;
	}
}
