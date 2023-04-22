package com.lairon.libs.xconfig.configs;

import com.lairon.libs.xconfig.Config;
import com.lairon.libs.xconfig.annotation.Description;
import com.lairon.libs.xconfig.annotation.Path;
import lombok.Getter;

@Path("deepConf")
@Getter
public class DeepConfig extends Config {

    @Path("deeeeeeep")
    @Description("super desc")
    private final String deep = "dip";


}
