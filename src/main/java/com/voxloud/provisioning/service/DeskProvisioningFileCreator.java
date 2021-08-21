package com.voxloud.provisioning.service;

public class DeskProvisioningFileCreator extends ProvisioningFileCreator {
	
	public DeskProvisioningFileCreator(ProvisioningConfiguration provisioningConfiguration) {
		super(provisioningConfiguration);
	}

	@Override
	public String getProvisioningFile(String overrideFragment) {
		overrideFragment.lines().forEach(line -> {
			configurationHolder.put(line.substring(0, line.indexOf("=")), 
					line.substring(line.indexOf("=")+1));
		});
		addPropertyConfigurations();
		return generateConfigurationString();
	}
	
	/**
	 * The method streams the HashMap containing the final configurations
	 * and constructs a StringBuilder instance by combining the key value 
	 * pairs with an equals operator "="
	 * @return the final configuration to be sent for devices
	 */
	private String generateConfigurationString() {
		StringBuilder configuration = new StringBuilder();
		configurationHolder.entrySet().stream().forEach(line -> {
			configuration.append(line.getKey()+"="+line.getValue()+"\n");
		});
		return configuration.toString();
	}
	
}
