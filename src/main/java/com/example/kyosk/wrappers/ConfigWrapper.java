package com.example.kyosk.wrappers;

import lombok.Data;

/**
 * @author bkariuki
 */
@Data
public class ConfigWrapper {
    private String name;
    private Boolean cpuEnabled;
    private Boolean monitoringEnabled;
    private String cpuValue;
}
