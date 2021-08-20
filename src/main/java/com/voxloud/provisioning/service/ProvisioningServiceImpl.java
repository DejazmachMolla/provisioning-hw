package com.voxloud.provisioning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.Device.DeviceModel;
import com.voxloud.provisioning.repository.DeviceRepository;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {
	
	private DeviceRepository deviceRepository;
	
	@Autowired
	public ProvisioningServiceImpl(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
    public String getProvisioningFile(String macAddress) {
    	Device device = this.deviceRepository.findByMacAddress(macAddress);
    	ProvisioningFileCreator provisioningFileCreator = null;
    	if(device.getModel().equals(DeviceModel.DESK)) {
    		provisioningFileCreator = new DeskProvisioningFileCreator();
    	} else if (device.getModel().equals(DeviceModel.CONFERENCE)) {
    		provisioningFileCreator = new ConferenceProvisioningFileCreator();
    	} else {
    		return null;
    	}
    	return provisioningFileCreator.getProvisioningFile(device);
    }
    
}
