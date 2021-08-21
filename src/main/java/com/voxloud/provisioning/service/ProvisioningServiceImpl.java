package com.voxloud.provisioning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxloud.provisioning.config.ProvisioningConfiguration;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.Device.DeviceModel;
import com.voxloud.provisioning.exceptions.NotFoundException;
import com.voxloud.provisioning.repository.DeviceRepository;
import com.voxloud.provisioning.service.filecreator.ConferenceProvisioningFileCreator;
import com.voxloud.provisioning.service.filecreator.DeskProvisioningFileCreator;
import com.voxloud.provisioning.service.filecreator.ProvisioningFileCreator;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {
	
	private DeviceRepository deviceRepository;
	
	private ProvisioningConfiguration provisioningConfiguration;
	
	@Autowired
	public ProvisioningServiceImpl(DeviceRepository deviceRepository,
			ProvisioningConfiguration provisioningConfiguration) {
		this.deviceRepository = deviceRepository;
		this.provisioningConfiguration = provisioningConfiguration;
	}
	
    public String getProvisioningFile(String macAddress) {
    	Device device = this.deviceRepository.findByMacAddress(macAddress);
    	if(device == null) {
    		throw new NotFoundException("Device not found.");
    	}
    	ProvisioningFileCreator provisioningFileCreator = null;
    	if(device.getModel().equals(DeviceModel.DESK)) {
    		provisioningFileCreator = new DeskProvisioningFileCreator(this.provisioningConfiguration);
    	} else if (device.getModel().equals(DeviceModel.CONFERENCE)) {
    		provisioningFileCreator = new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
    	} else {
    		return null;
    	}
    	return provisioningFileCreator.getProvisioningFile(device);
    }
    
}
