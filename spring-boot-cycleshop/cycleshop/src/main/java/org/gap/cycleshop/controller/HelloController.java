package org.gap.cycleshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	String HTML_TEMPLATE = """
			<!DOCTYPE html>
			<html lang="en">
				<head>
					<meta charset="utf-8">
				</head>
				<body>
					<h1>Welcome~!</h1>
					<p>Hello %s</p>
				</body>
			</html>
			""";

    @GetMapping("/sayhello")
	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
		return String.format(HTML_TEMPLATE, name);
	}
}
