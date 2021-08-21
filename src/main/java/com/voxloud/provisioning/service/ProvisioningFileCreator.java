package com.voxloud.provisioning.service;

import java.util.HashMap;
import java.util.Map;

public abstract class ProvisioningFileCreator {	
	
	protected ProvisioningConfiguration provisioningConfiguration;
	protected Map<String, String> configurationHolder = new HashMap<String, String>();
	protected abstract String getProvisioningFile(String overrideFragment);
	
	public ProvisioningFileCreator(ProvisioningConfiguration provisioningConfiguration) {
		this.provisioningConfiguration = provisioningConfiguration;
	}
	
	/**
	 * The method adds the configurations found in the property file
	 * to a map tracking all the configurations
	 */
	protected void addPropertyConfigurations() {
		if(!configurationHolder.containsKey("domain")) {
			configurationHolder.put("domain", provisioningConfiguration.getDomain());
		}
		if(!configurationHolder.containsKey("port")) {
			configurationHolder.put("port", provisioningConfiguration.getPort());
		}
		if(!configurationHolder.containsKey("codecs")) {
			configurationHolder.put("codecs", provisioningConfiguration.getCodecs());
		}
	}
	
}
