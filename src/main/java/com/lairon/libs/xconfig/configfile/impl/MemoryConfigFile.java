package com.lairon.libs.xconfig.configfile.impl;

import com.lairon.libs.xconfig.configfile.ConfigFile;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MemoryConfigFile implements ConfigFile {

    private Map<String, Object> data = new HashMap<>();
    private Map<String, List<String>> comments = new HashMap<>();

    @Override
    public void load(@NonNull File file) {

    }

    @Override
    public boolean contains(@NonNull String path) {
        return data.containsKey(path);
    }

    @Override
    public void set(@NonNull String path, @NonNull Object data) {
        this.data.put(path, data);
    }

    @Override
    public void setComments(@NonNull String path, @NonNull List<String> comments) {
        this.comments.put(path, comments);
    }

    @Override
    public void save(@NonNull File file) {}

    @Override
    public Object get(@NonNull String path, Object defaultValue) {
        Object o = get(path);
        return o == null ? defaultValue : o;
    }

    @Override
    public Object get(@NonNull String path) {
        return data.get(path);
    }
}
