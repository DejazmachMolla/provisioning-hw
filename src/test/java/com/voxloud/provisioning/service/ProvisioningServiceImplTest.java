package com.voxloud.provisioning.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.Device.DeviceBuilder;
import com.voxloud.provisioning.entity.Device.DeviceModel;
import com.voxloud.provisioning.repository.DeviceRepository;
@SpringBootTest(webEnvironment = NONE)
@ActiveProfiles("test")
class ProvisioningServiceImplTest {
	
	@MockBean
	private DeviceRepository deviceRepository;
	
	@Autowired
	private ProvisioningServiceImpl provisioningService;
	

	@Test
	void testGetProvisioningFile() {
		//given
        String macAddress = "DMAC";
        DeviceBuilder deviceBuilder = Device.builder()
    			.username("Dejazmach")
    			.macAddress("DMAC")
    			.overrideFragment(null)
    			.password("password");
        
        given(deviceRepository.findByMacAddress("DMAC"))
        	.willReturn(Optional.of(deviceBuilder.model(DeviceModel.DESK)
			.build()));
        String configuration1 = this.provisioningService.getProvisioningFile(macAddress);
        
        given(deviceRepository.findByMacAddress("DMAC"))
    	.willReturn(Optional.of(deviceBuilder.model(DeviceModel.CONFERENCE)
		.build()));

        //when
        String configuration2 = this.provisioningService.getProvisioningFile(macAddress);

        //then
        then(configuration1).isNotNull();
        then(configuration2).isNotNull();
        then(configuration1.lines().count()).isEqualTo(5);
        then(configuration2.lines().count()).isEqualTo(1);
	}

}
