package com.example.kyosk.services;

import com.example.kyosk.entities.MainConfig;
import com.example.kyosk.repositories.ConfigRepository;
import com.example.kyosk.repositories.CpuRepository;
import com.example.kyosk.repositories.MonitoringRepository;
import com.example.kyosk.wrappers.ConfigImp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bkariuki
 */
@Service
public class ConfigService implements ConfigImp {
    private final ConfigRepository configRepository;
    private final CpuRepository cpuRepository;
    private final MonitoringRepository monitoringRepository;

    public ConfigService(ConfigRepository configRepository, CpuRepository cpuRepository,
                         MonitoringRepository monitoringRepository) {
        this.configRepository = configRepository;
        this.cpuRepository = cpuRepository;
        this.monitoringRepository = monitoringRepository;
    }

    @Override
    public MainConfig create(MainConfig config) {
        return configRepository.save(config);
    }

    @Override
    public List<MainConfig> getConfigs() {
        return configRepository.findAll();
    }

    @Override
    public MainConfig getConfigByName(String name) {
        return null;
    }
}
