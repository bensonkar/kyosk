package com.example.kyosk.wrappers;

import com.example.kyosk.entities.MainConfig;

import java.util.List;

/**
 * @author bkariuki
 */
public interface ConfigImp {
    MainConfig create(MainConfig config);
    List<MainConfig> getConfigs();
    MainConfig getConfigByName(String name);
}
