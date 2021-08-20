package com.voxloud.provisioning.service;

import com.voxloud.provisioning.entity.Device;

public abstract class ProvisioningFileCreator {
	abstract String getProvisioningFile(Device device);
}
