package com.example.kyosk.resources;

import com.example.kyosk.entities.*;
import com.example.kyosk.repositories.*;
import com.example.kyosk.wrappers.ConfigImp;
import com.example.kyosk.wrappers.ConfigWrapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
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
    private final ConfigRepository configRepository;

    public ConfigResource(ConfigImp configImp, CpuRepository cpuRepository, MonitoringRepository monitoringRepository,
                          LimitsRepository limitsRepository, MetaDataRepository metaDataRepository, ConfigRepository configRepository) {
        this.configImp = configImp;
        this.cpuRepository = cpuRepository;
        this.monitoringRepository = monitoringRepository;
        this.limitsRepository = limitsRepository;
        this.metaDataRepository = metaDataRepository;
        this.configRepository = configRepository;
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
            configRepository.save(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/configs")
    public ResponseEntity<List<MainConfig>> all() {
        return ResponseEntity.ok(configImp.getConfigs());
    }

    @PostMapping("/configs")
    public ResponseEntity create(@RequestBody ConfigWrapper wrapper) {
        if (configImp.getConfigByName(wrapper.getName()) != null) {
            return new ResponseEntity("record with provided name already exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(configImp.create(wrapper), HttpStatus.CREATED);
    }

    @GetMapping("/configs/{name}")
    public ResponseEntity getConfigByName(@PathVariable String name) {
        if (configImp.getConfigByName(name) == null) {
            return new ResponseEntity("no record found with provided name", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configImp.getConfigByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/configs/{name}")
    public ResponseEntity deleteByName(@PathVariable String name) {
        if (configImp.getConfigByName(name) == null) {
            return new ResponseEntity("no record found with provided name", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(configImp.deleteByName(name), HttpStatus.OK);
    }

    @PutMapping("/configs/{name}")
    public ResponseEntity updateConfig(@PathVariable String name, @RequestBody ConfigWrapper wrapper) {
        if (configImp.getConfigByName(name) == null) {
            return new ResponseEntity("no record found with provided name", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(configImp.updateConfig(wrapper, name));
    }

    @GetMapping("/search")
    public ResponseEntity query(MainConfig config) {
        List<MainConfig> configList = new ArrayList<>();
        if (config.getMetadata() != null) {
            Boolean monitoringEnabled = config.getMetadata().getMonitoring().getEnabled();
            configList = configRepository.findAllByMetadataMonitoringEnabled(monitoringEnabled);
            return ResponseEntity.ok(configList);
        }
        if (config.getLimits() != null) {
            if (config.getLimits().getCpu().getEnabled() != null) {
                Boolean cpuEnabled = config.getLimits().getCpu().getEnabled();
                configList = configRepository.findAllByLimitsCpuEnabled(cpuEnabled);
                return ResponseEntity.ok(configList);
            }
            if (config.getLimits().getCpu().getValue() != null) {
                String cpuValue = config.getLimits().getCpu().getValue();
                configList = configRepository.findAllByLimitsCpuValue(cpuValue);
                return ResponseEntity.ok(configList);
            }
        }
        return new ResponseEntity(configList, HttpStatus.OK);
    }
}
