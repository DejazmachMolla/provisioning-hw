package com.voxloud.provisioning.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.Device.DeviceModel;

@SpringBootTest
@ActiveProfiles("test")
class DeviceRepositoryTest {
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(deviceRepository).isNotNull();
	}
	
	@Test
	void testFindByMacAddress() {
		//given
        Device newDevice = Device.builder()
				.username("Dejazmach")
				.macAddress("DMAC")
				.model(DeviceModel.DESK)
				.overrideFragment(null)
				.password("password")
				.build();
        deviceRepository.save(newDevice);
        
        //when
        Device device = deviceRepository.findByMacAddress("DMAC");

        //then
        then(device.getUsername()).isNotNull();
        then(device.getUsername()).isEqualTo("Dejazmach");
	}
}
