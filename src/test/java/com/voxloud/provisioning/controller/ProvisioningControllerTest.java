package com.voxloud.provisioning.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.voxloud.provisioning.exceptions.NotFoundException;
import com.voxloud.provisioning.exceptions.RestControllerAdvice;
import com.voxloud.provisioning.service.ProvisioningServiceImpl;

@WebMvcTest
@ActiveProfiles("test")
class ProvisioningControllerTest {
	
	@MockBean
    private ProvisioningServiceImpl provisioningServiceImpl;

    @Autowired
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ProvisioningController.class)
            .setControllerAdvice(new RestControllerAdvice())
            .build();

	@Test
	void testProvision_forExistingDevice() throws Exception {
		//given
        given(provisioningServiceImpl.getProvisioningFile(anyString())).willReturn(
                "{\"password\":\"red\",\"port\":\"5060\",\"domain\":\"sip.voxloud.com\",\"codecs\":\"[\\\"G711\\\",\\\"G729\\\",\\\"OPUS\\\"]\",\"username\":\"sofia\"}"
        );

        //when then
        mockMvc.perform(get("/api/v1/provisioning/f1-e2-d3-c4-b5-a6")								
            .content("{\"password\":\"red\",\"port\":\"5060\",\"domain\":\"sip.voxloud.com\",\"codecs\":\"[\\\"G711\\\",\\\"G729\\\",\\\"OPUS\\\"]\",\"username\":\"sofia\"}"))
        	.andExpect(status().isOk());
	}
	
	@Test
    void testProvision_ForMissingDevice_status404() throws Exception {
        //given
        given(this.provisioningServiceImpl.getProvisioningFile(anyString()))
        		.willThrow(new NotFoundException("Device not found!"));

        //when then
        mockMvc.perform(get("/api/v1/provisioning/ab-cd-ef-gh-ij-kl"))
               .andExpect(status().isNotFound());

    }

}
