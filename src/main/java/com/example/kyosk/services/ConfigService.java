package com.example.kyosk.services;

import com.example.kyosk.entities.*;
import com.example.kyosk.repositories.*;
import com.example.kyosk.wrappers.ConfigImp;
import com.example.kyosk.wrappers.ConfigWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bkariuki
 */
@Service
public class ConfigService implements ConfigImp {
    private final CpuRepository cpuRepository;
    private final MonitoringRepository monitoringRepository;
    private final LimitsRepository limitsRepository;
    private final MetaDataRepository metaDataRepository;
    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository, CpuRepository cpuRepository,
                         MonitoringRepository monitoringRepository, LimitsRepository limitsRepository, MetaDataRepository metaDataRepository) {
        this.limitsRepository = limitsRepository;
        this.metaDataRepository = metaDataRepository;
        this.configRepository = configRepository;
        this.cpuRepository = cpuRepository;
        this.monitoringRepository = monitoringRepository;
    }

    @Override
    public MainConfig create(ConfigWrapper wrapper) {
        try {
            MainConfig config = new MainConfig();
            config.setName(wrapper.getName());
            Monitoring monitoring = new Monitoring();
            monitoring.setEnabled(wrapper.getMonitoringEnabled());
            monitoringRepository.save(monitoring);
            Cpu cpu = new Cpu();
            cpu.setEnabled(wrapper.getCpuEnabled());
            cpu.setValue(wrapper.getCpuValue());
            cpuRepository.save(cpu);
            Limits limits = new Limits();
            limits.setCpu(cpu);
            limitsRepository.save(limits);
            config.setLimits(limits);
            Metadata metadata = new Metadata();
            metadata.setMonitoring(monitoring);
            metaDataRepository.save(metadata);
            config.setMetadata(metadata);
            return configRepository.save(config);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<MainConfig> getConfigs() {
        return configRepository.findAll();
    }

    @Override
    public MainConfig getConfigByName(String name) {
        try {
            return configRepository.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteByName(String name) {
        try {
            Long id = getConfigByName(name).getId();
            configRepository.deleteById(id);
            return "record deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "record failed to delete";
        }
    }

    @Override
    public MainConfig updateConfig(ConfigWrapper wrapper, String name) {
        MainConfig config = getConfigByName(name);
        if (config != null) {
            if (wrapper.getName() != null) {
                config.setName(wrapper.getName());
            }
            if (wrapper.getMonitoringEnabled() != null) {
                Monitoring monitoring = new Monitoring();
                monitoring.setEnabled(wrapper.getMonitoringEnabled());
                monitoringRepository.save(monitoring);
                Metadata metadata = new Metadata();
                metadata.setMonitoring(monitoring);
                metaDataRepository.save(metadata);
                config.setMetadata(metadata);
            }
            if (wrapper.getCpuEnabled() != null) {
                Cpu cpu = new Cpu();
                cpu.setEnabled(wrapper.getCpuEnabled());
                cpu.setValue(wrapper.getCpuValue());
                cpuRepository.save(cpu);
                Limits limits = new Limits();
                limits.setCpu(cpu);
                limitsRepository.save(limits);
                config.setLimits(limits);
            }

        }
        return config;
    }
}
