package com.voxloud.provisioning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProvisioningConfiguration {

	public String domain;
		
	public String port;
		
	public String codecs;

    @Autowired
    public ProvisioningConfiguration(@Value("${provisioning.domain}") String domain, 
    		@Value("${provisioning.port}") String port,
    		@Value("${provisioning.codecs}") String codecs) {
        this.domain = domain;
		this.port = port;
		this.codecs = codecs;
    }

	public String getDomain() {
		return domain;
	}

	public String getPort() {
		return port;
	}

	public String getCodecs() {
		return codecs;
	}
}