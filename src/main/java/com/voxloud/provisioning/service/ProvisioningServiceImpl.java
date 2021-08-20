package com.voxloud.provisioning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxloud.provisioning.repository.DeviceRepository;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {
	
	private DeviceRepository deviceRepository;
	
	@Autowired
	public ProvisioningServiceImpl(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
    public String getProvisioningFile(String macAddress) {
        return this.deviceRepository.getProvisioningFile(macAddress);
    }
}
