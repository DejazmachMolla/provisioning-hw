package com.voxloud.provisioning.service;

import com.google.gson.Gson;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.OverrideFragment;

public class ConferenceProvisioningFileCreator extends ProvisioningFileCreator {
	
	public ConferenceProvisioningFileCreator(ProvisioningConfiguration provisioningConfiguration) {
		super(provisioningConfiguration);
	}

	@Override
	protected String getProvisioningFile(Device device) {
		String overrideFragment = device.getOverrideFragment();
		Gson g = new Gson();
		OverrideFragment of = g.fromJson(overrideFragment, OverrideFragment.class);
		configurationHolder.put("domain", of.getDomain());
		configurationHolder.put("port", of.getPort());
		configurationHolder.put("timeout", of.getTimeout());
		addUsenameAndPassword(device);
		addPropertyConfigurations();
		return generateConfigurationString(g);
	}
	
	/**
	 * The method uses Gson library and 
	 * - converts the HashMap containing the final configurations to json
	 * - converts the codecs array to json
	 * and constructs a json object
	 * @return the final configuration to be sent for Conference devices
	 */
	protected String generateConfigurationString(Gson g) {
		String codecsStr = configurationHolder.get("codecs");
		String[] codecsItems = codecsStr.split(",");
		configurationHolder.put("codecs", g.toJson(codecsItems));
		return g.toJson(configurationHolder);
	}

}
