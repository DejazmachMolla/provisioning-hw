package com.voxloud.provisioning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<com.voxloud.provisioning.entity.Device, String> {
	@Query("select device.overrideFragment from Device device where device.macAddress = :macAddress")
	String getProvisioningFile(@Param("macAddress")String macAddress);
}
