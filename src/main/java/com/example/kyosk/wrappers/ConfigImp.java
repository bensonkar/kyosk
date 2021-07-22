package com.example.kyosk.wrappers;

import com.example.kyosk.entities.MainConfig;

import java.util.List;

/**
 * @author bkariuki
 */
public interface ConfigImp {
    MainConfig create(ConfigWrapper wrapper);

    List<MainConfig> getConfigs();

    MainConfig getConfigByName(String name);

    String deleteByName(String name);

    MainConfig updateConfig(ConfigWrapper wrapper, String name);
}
