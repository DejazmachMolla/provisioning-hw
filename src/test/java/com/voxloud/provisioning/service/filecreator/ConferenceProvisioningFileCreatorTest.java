package com.voxloud.provisioning.service.filecreator;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.voxloud.provisioning.config.ProvisioningConfiguration;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.Device.DeviceBuilder;
import com.voxloud.provisioning.entity.Device.DeviceModel;

@SpringBootTest(webEnvironment = NONE)
@ActiveProfiles("test")
class ConferenceProvisioningFileCreatorTest {
	private DeviceBuilder deviceBuilder = Device.builder()
		.username("Dejazmach")
		.macAddress("DMAC")
		.model(DeviceModel.CONFERENCE)
		.password("password");
	
	@Autowired
	private ProvisioningConfiguration provisioningConfiguration;
	
	private ConferenceProvisioningFileCreator conferenceProvisioningFileCreator; 
	private Map<String, String> configurationHolder;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Checks the provisioning file when overrideFragment is available
	 */
	@Test
	void testGetProvisioningFile_overrideFragment() {
		//given
		conferenceProvisioningFileCreator = 
				new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
		Device device = this.deviceBuilder
				.overrideFragment("{\"domain\":\"sip.anotherdomain.com\",\"port\":\"5161\",\"timeout\":10}")
				.build();
		
		//when
		String configuration = this.conferenceProvisioningFileCreator.getProvisioningFile(device);
		
		//then
		then(configuration.contains("\"domain\":\"sip.anotherdomain.com\"")).isEqualTo(true);
	}
	
	/**
	 * Checks the provisioning file when overrideFragment is null
	 */
	@Test
	void testGetProvisioningFile_noOverrideFragment() {
		//given
		conferenceProvisioningFileCreator = 
				new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
		Device device = this.deviceBuilder
				.overrideFragment(null)
				.build();
		
		//when
		String configuration = this.conferenceProvisioningFileCreator.getProvisioningFile(device);
		
		//then
		then(configuration.contains("sip.voxloud.com")).isEqualTo(true);
	}
	
	/**
	 * Checks the addPropertyConfigurations method when overrideFragment
	 * is available
	 */
	@Test
	void testAddPropertyConfigurations_overrideFragment() {
		//given
		conferenceProvisioningFileCreator = 
				new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
		conferenceProvisioningFileCreator.configurationHolder = 
				this.getConfigurationForAddPropertyConfiguration_withOverrideFragment();
		
		//when
		this.conferenceProvisioningFileCreator.addPropertyConfigurations();
		
		//then
		then(this.configurationHolder.get("codecs")).isEqualTo("G711,G729,OPUS");
		then(this.configurationHolder.get("domain")).isEqualTo("dejazmach.anotherdomain.com");
		then(this.configurationHolder.get("username")).isEqualTo("Deju");
		then(this.configurationHolder.get("password")).isEqualTo("Dpass");
		
	}
	
	/**
	 * Checks the addPropertyConfigurations method when overrideFragment 
	 * is null
	 */
	@Test
	void testAddPropertyConfigurations_noOverrideFragment() {
		//given
		conferenceProvisioningFileCreator = 
				new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
		conferenceProvisioningFileCreator.configurationHolder = 
				this.getConfigurationForAddPropertyConfiguration_withNoOverrideFragment();
		
		//when
		this.conferenceProvisioningFileCreator.addPropertyConfigurations();
		
		//then
		then(this.configurationHolder.get("domain")).isEqualTo("sip.voxloud.com");
		then(this.configurationHolder.get("username")).isEqualTo("Deju");
		then(this.configurationHolder.get("password")).isEqualTo("Dpass");
	}
	
	/**
	 * Checks whether or not the configurationHolder gets appropriate username and password
	 */
	@Test
	void testAddUsenameAndPassword() {
		//given
		conferenceProvisioningFileCreator = 
				new ConferenceProvisioningFileCreator(this.provisioningConfiguration);
		conferenceProvisioningFileCreator.configurationHolder = 
				this.getConfigurationForAddPropertyConfiguration_withNoOverrideFragment();
		
		
		//when
		this.conferenceProvisioningFileCreator.addUsenameAndPassword(this.deviceBuilder
				.overrideFragment(null)
				.build());
		
		//then
		then(this.configurationHolder.get("username")).isEqualTo("Dejazmach");
		then(this.configurationHolder.get("password")).isEqualTo("password");
	}
	
	private Map<String, String> getConfigurationForAddPropertyConfiguration_withOverrideFragment() {
		this.configurationHolder = new HashMap<String, String>();
		this.configurationHolder.put("model", "CONFERENCE");
		this.configurationHolder.put("macAddress", "1234");
		this.configurationHolder.put("domain", "dejazmach.anotherdomain.com");
		this.configurationHolder.put("port", "4200");
		this.configurationHolder.put("username", "Deju");
		this.configurationHolder.put("password", "Dpass");
		return this.configurationHolder;
	}
	
	private Map<String, String> getConfigurationForAddPropertyConfiguration_withNoOverrideFragment() {
		this.configurationHolder = new HashMap<String, String>();
		this.configurationHolder.put("model", "CONFERENCE");
		this.configurationHolder.put("macAddress", "1234");
		this.configurationHolder.put("username", "Deju");
		this.configurationHolder.put("password", "Dpass");
		return this.configurationHolder;
	}
}