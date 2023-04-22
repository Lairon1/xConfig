package com.lairon.libs.xconfig.model;

import com.lairon.libs.xconfig.Config;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

@AllArgsConstructor
@Data
public class ConfigEntry {

    private final String path;
    private final Field field;
    private Object defaultValue;
    private Config configClass;
    private String[] comments;

}
