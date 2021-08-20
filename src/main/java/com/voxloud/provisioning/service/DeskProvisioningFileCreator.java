package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;

public class DeskProvisioningFileCreator extends ProvisioningFileCreator {
	
	@Override
	public String getProvisioningFile(Device device) {
		return device.getModel().toString();
	}
	
}
