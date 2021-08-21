package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;

public class ConferenceProvisioningFileCreator extends ProvisioningFileCreator {
	
	public ConferenceProvisioningFileCreator(ProvisioningConfiguration provisioningConfiguration) {
		super(provisioningConfiguration);
	}

	@Override
	protected String getProvisioningFile(Device device) {
		String overrideFragment = device.getOverrideFragment();
		
		return overrideFragment;
	}

	protected String generateConfigurationString() {
		// TODO Auto-generated method stub
		return null;
	}

}
