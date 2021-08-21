package com.voxloud.provisioning.service.filecreator;

import java.util.HashMap;
import java.util.Map;

import com.voxloud.provisioning.config.ProvisioningConfiguration;
import com.voxloud.provisioning.entity.Device;

public abstract class ProvisioningFileCreator {
	
	protected ProvisioningConfiguration provisioningConfiguration;
	protected Map<String, String> configurationHolder = new HashMap<String, String>();
	public abstract String getProvisioningFile(Device device);
	
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
	
	protected void addUsenameAndPassword(Device device) {
		configurationHolder.put("username", device.getUsername());
		configurationHolder.put("password", device.getPassword());
	}
	
}
