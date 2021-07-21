package com.example.kyosk.resources;

import com.example.kyosk.entities.*;
import com.example.kyosk.repositories.CpuRepository;
import com.example.kyosk.repositories.LimitsRepository;
import com.example.kyosk.repositories.MetaDataRepository;
import com.example.kyosk.repositories.MonitoringRepository;
import com.example.kyosk.wrappers.ConfigImp;
import com.example.kyosk.wrappers.ConfigWrapper;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author bkariuki
 */
@RestController
public class ConfigResource {
    private final ConfigImp configImp;
    private final CpuRepository cpuRepository;
    private final MonitoringRepository monitoringRepository;
    private final LimitsRepository limitsRepository;
    private final MetaDataRepository metaDataRepository;

    public ConfigResource(ConfigImp configImp, CpuRepository cpuRepository, MonitoringRepository monitoringRepository,
                          LimitsRepository limitsRepository, MetaDataRepository metaDataRepository) {
        this.configImp = configImp;
        this.cpuRepository = cpuRepository;
        this.monitoringRepository = monitoringRepository;
        this.limitsRepository = limitsRepository;
        this.metaDataRepository = metaDataRepository;
    }

    @PostConstruct
    public void test() {
       try {
           MainConfig config = new MainConfig();
           config.setName("test");
           Monitoring monitoring = new Monitoring();
           monitoring.setEnabled(true);
           monitoringRepository.save(monitoring);
           Cpu cpu = new Cpu();
           cpu.setEnabled(true);
           cpu.setValue("5000");
           cpuRepository.save(cpu);
           Limits limits = new Limits();
           limits.setCpu(cpu);
           limitsRepository.save(limits);
           config.setLimits(limits);
           Metadata metadata = new Metadata();
           metadata.setMonitoring(monitoring);
           metaDataRepository.save(metadata);
           config.setMetadata(metadata);
           configImp.create(config);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @GetMapping("/configs")
    public List<MainConfig> all() {
        return configImp.getConfigs();
    }

    @PostMapping("/configs")
    public MainConfig create(@RequestBody ConfigWrapper wrapper) {
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
         config = configImp.create(config);
         return config;
    }

    @GetMapping("/configs/{name}")
    public MainConfig getConfigByName(@PathVariable String name) {
        return configImp.getConfigByName(name);
    }

    @DeleteMapping("/configs/{name}")
    public String deleteByName(@PathVariable String name){
        return configImp.deleteByName(name);
    }
}
