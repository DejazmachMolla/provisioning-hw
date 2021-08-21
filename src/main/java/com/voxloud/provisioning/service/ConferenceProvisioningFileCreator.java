package com.voxloud.provisioning.service;

public class ConferenceProvisioningFileCreator extends ProvisioningFileCreator {
	
	public ConferenceProvisioningFileCreator(ProvisioningConfiguration provisioningConfiguration) {
		super(provisioningConfiguration);
	}

	@Override
	protected String getProvisioningFile(String overrideFragment) {
		return overrideFragment;
	}

	protected String generateConfigurationString() {
		// TODO Auto-generated method stub
		return null;
	}

}
