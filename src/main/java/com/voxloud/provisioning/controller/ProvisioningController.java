package com.voxloud.provisioning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voxloud.provisioning.service.ProvisioningServiceImpl;


@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {
	private ProvisioningServiceImpl provisioningServiceImple;
	public ProvisioningController() {
		
	}
	@Autowired
	public ProvisioningController(ProvisioningServiceImpl provisioningServiceImple) {
		this.provisioningServiceImple = provisioningServiceImple;
	}
	
	@RequestMapping(value="/provisioning/{macAddress}", method = RequestMethod.GET)
	public String provision(@PathVariable("macAddress")String macAddress) {
		
		return this.provisioningServiceImple.getProvisioningFile(macAddress);
	}
}