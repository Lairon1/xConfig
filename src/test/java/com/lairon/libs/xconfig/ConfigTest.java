package com.lairon.libs.xconfig;

import com.lairon.libs.xconfig.configfile.impl.MemoryConfigFile;
import com.lairon.libs.xconfig.configs.SettingsConfig;
import org.junit.jupiter.api.Test;

import java.io.File;


class ConfigTest {


    private final File resourceFolder = new File("src/test/resources");

    @Test
    void testLoadingConfigs() {
        File path = new File(resourceFolder + File.separator + "testLoadingConfigs.yml");
        SettingsConfig config = new SettingsConfig(path);
        config.reload();

        MemoryConfigFile mcf = (MemoryConfigFile) config.getConfig();
        mcf.getData().forEach((s, o) -> System.out.println(s + ": " + o));

        path.delete();
    }
}