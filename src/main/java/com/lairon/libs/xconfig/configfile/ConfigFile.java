package com.lairon.libs.xconfig.configfile;

import lombok.NonNull;

import java.io.File;
import java.util.List;

public interface ConfigFile {

    void load(@NonNull File file);

    boolean contains(@NonNull String path);

    void set(@NonNull String path, @NonNull Object data);

    void setComments(@NonNull String path, @NonNull List<String> comments);

    void save(@NonNull File file);

    Object get(@NonNull String path, Object defaultValue);
    Object get(@NonNull String path);


}
