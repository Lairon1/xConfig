package com.lairon.libs.xconfig;

import com.lairon.libs.xconfig.configfile.impl.MemoryConfigFile;
import com.lairon.libs.xconfig.configs.SettingsConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;


class ConfigTest {


    private final File resourceFolder = new File("src/test/resources");

    @Test
    void testLoadingConfigs() {
        File path = new File(resourceFolder + File.separator + "testLoadingConfigs.yml");
        SettingsConfig config = new SettingsConfig(path);
        config.reload();

        MemoryConfigFile mcf = (MemoryConfigFile) config.getConfig();

        Map<String, Object> data = mcf.getData();

        Assert.assertEquals(data.get("Settings.TestMMessageString"), "testMMessageeee");
        Assert.assertEquals(data.get("Settings.TestNumber"), 11.5123f);
        Assert.assertEquals(data.get("Settings.deepConf.deeeeeeep"), "dip");

        path.delete();
    }
}