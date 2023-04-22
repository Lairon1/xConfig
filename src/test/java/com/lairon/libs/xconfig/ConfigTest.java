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

        Assert.assertEquals("testMMessageeee", data.get("Settings.TestMMessageString"));
        Assert.assertEquals(11.5123f, data.get("Settings.TestNumber"));
        Assert.assertEquals("dip", data.get("Settings.deepConf.deeeeeeep"));

        path.delete();
    }
}