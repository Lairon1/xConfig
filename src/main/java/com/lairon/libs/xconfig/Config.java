package com.lairon.libs.xconfig;

import com.lairon.libs.xconfig.annotation.Description;
import com.lairon.libs.xconfig.annotation.Path;
import com.lairon.libs.xconfig.configfile.ConfigFile;
import com.lairon.libs.xconfig.model.ConfigEntry;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    private File file;
    @Getter
    private ConfigFile config;
    private List<ConfigEntry> entries = new ArrayList<>();
    private boolean moduleLoaded = false;

    @SneakyThrows
    public Config(@NonNull File file, Class<? extends ConfigFile> module) {
        if(module == null) return;
        moduleLoaded = true;
        this.file = file;
        config = module.getConstructor().newInstance();
        loadConfigEntries(this, "");
    }

    public Config(@NonNull String path, Class<? extends ConfigFile> module) {
        this(new File(path), module);
    }

    public Config() {
        this(new File("config.yml"), null);
    }

    public Config reload() {
        if (!moduleLoaded)
            throw new IllegalArgumentException("File configuration module is not loaded for " + getClass().getSimpleName());
        reloadFiles();
        updateValuesInClass();
        return this;
    }

    @SneakyThrows
    private void reloadFiles() {
        boolean save = false;

        if (!file.exists()) {
            Files.createDirectories(file.getParentFile().toPath());
            file.createNewFile();
            save = true;
        }

        config.load(file);

        if (save) updateValuesInFile();
    }

    @SneakyThrows
    private void updateValuesInFile() {
        for (ConfigEntry entry : entries) {
            if(config.contains(entry.getPath())) continue;
            config.set(entry.getPath(), entry.getDefaultValue());
            if (entry.getComments() != null)
                config.setComments(entry.getPath(), Arrays.asList(entry.getComments()));
        }
        config.save(file);
    }

    @SneakyThrows
    private void updateValuesInClass() {
        for (ConfigEntry entry : entries) {
            Object data = config.get(entry.getPath(), entry.getDefaultValue());
            Field field = entry.getField();
            field.setAccessible(true);
            field.set(entry.getConfigClass(), data);
            field.setAccessible(false);
        }
    }


    private void loadConfigEntries(@NonNull Config config, @NonNull String path) {
        Path pathAnnotation = config.getClass().getAnnotation(Path.class);

        if (pathAnnotation != null) path += pathAnnotation.value();

        for (Field field : config.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            loadConfigEntry(field, config, path);
            field.setAccessible(false);
        }
        for (Field field : config.getClass().getFields()) {
            loadConfigEntry(field, config, path);
        }
    }

    @SneakyThrows
    private void loadConfigEntry(@NonNull Field field, @NonNull Config config, @NonNull String path) {

        Object data = field.get(config);
        if (data == null) return;

        if (data instanceof Config deepConfig) {
            loadConfigEntries(deepConfig, path);
            return;
        }

        Path pathAnnotation = field.getAnnotation(Path.class);
        if (pathAnnotation == null) return;
        Description descriptionAnnotation = field.getAnnotation(Description.class);

        entries.add(new ConfigEntry(
                path + pathAnnotation.value(),
                field,
                data,
                config,
                descriptionAnnotation != null ? descriptionAnnotation.value() : null
        ));
    }

}
