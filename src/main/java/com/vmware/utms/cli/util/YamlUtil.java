package com.vmware.utms.cli.util;

import com.vmware.utms.cli.models.yamlImport.YamlDto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface YamlUtil {
    void traverseSuites(YamlDto yaml);

    YamlDto getYamlDtoFromYamlFile(String filePath) throws IOException;

    YamlDto getYamlDtoFromYamlFile(File file) throws IOException;

    YamlDto getYamlDtoFromYamlFile(URL url) throws IOException;

    YamlDto getYamlDtoFromYamlFile(InputStream inputStream) throws IOException;

    boolean checkYamlCompatibility(File file, Class<?> aClass) throws IOException;

    boolean checkYamlCompatibility(InputStream inputStream, Class<?> aClass) throws IOException;
}