package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;

public class ConferenceProvisioningFileCreator extends ProvisioningFileCreator {

	@Override
	String getProvisioningFile(Device device) {
		// TODO Auto-generated method stub
		return device.getModel().toString();
	}

}
