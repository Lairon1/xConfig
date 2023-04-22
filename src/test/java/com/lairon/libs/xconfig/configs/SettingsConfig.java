package com.lairon.libs.xconfig.configs;

import com.lairon.libs.xconfig.Config;
import com.lairon.libs.xconfig.annotation.Path;
import com.lairon.libs.xconfig.configfile.ConfigFile;
import com.lairon.libs.xconfig.configfile.impl.MemoryConfigFile;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;

@Path("Settings.")
@Getter
public class SettingsConfig extends Config {

    @Path("TestMMessageString")
    private String testMessage = "testMMessageeee";

    @Path("TestNumber")
    private double testNumber = 11.5123f;

    private DeepConfig deep = new DeepConfig();


    public SettingsConfig(@NonNull File file) {
        super(file, MemoryConfigFile.class);
    }

    public SettingsConfig(@NonNull String path) {
        super(path, MemoryConfigFile.class);
    }
}
