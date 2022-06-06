package pl.polsl.hdised.consumer.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query(value = "SELECT DISTINCT * \n" +
            "FROM device\n" +
            "WHERE device.device_id = :deviceId\n" +
            "LIMIT 1\n", nativeQuery = true)
    DeviceEntity findDeviceById(@Param("deviceId") String deviceId);

}
