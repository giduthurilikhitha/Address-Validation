package com.location.validation;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZAddressController {

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
	@GetMapping("/address/{address}")
	public String validateAndGet(@PathVariable String address) throws IOException {
		AddressService service = new AddressService();
		return service.validateAndGetAddress(address);
	}
}
