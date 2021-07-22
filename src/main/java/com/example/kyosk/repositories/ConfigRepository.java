package com.example.kyosk.repositories;

import com.example.kyosk.entities.MainConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface ConfigRepository extends JpaRepository<MainConfig, Long> {
    MainConfig findByName(String name);
    MainConfig deleteByName(String name);
    List<MainConfig> findAllByMetadataMonitoringEnabled(Boolean enabled);
    List<MainConfig> findAllByLimitsCpuValue(String value);
    List<MainConfig> findAllByLimitsCpuEnabled(Boolean enabled);

}
