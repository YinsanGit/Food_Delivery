package org.example.ytlearning.repository;

import org.example.ytlearning.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    boolean existsByDeviceId(String deviceId);

    // or if you want to check uniqueness per user:
    boolean existsByDeviceIdAndUserId(String deviceId, Long userId);
}
