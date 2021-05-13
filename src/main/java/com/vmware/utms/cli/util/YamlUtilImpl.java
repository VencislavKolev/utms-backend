package com.vmware.utms.cli.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.vmware.utms.cli.models.yamlImport.ImportSuiteDto;
import com.vmware.utms.cli.models.yamlImport.ImportSuiteTestDto;
import com.vmware.utms.cli.models.yamlImport.YamlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

@Component
public class YamlUtilImpl implements YamlUtil {

    private final FileUtil fileUtil;
    //private final ObjectMapper objectMapper;

    @Autowired
    public YamlUtilImpl(FileUtil fileUtil/*, ObjectMapper objectMapper*/) {
        this.fileUtil = fileUtil;
        //this.objectMapper = objectMapper;
    }

    @Override
    public void traverseSuites(YamlDto yaml) {
        for (ImportSuiteDto suite : yaml.getSuites()) {
            suite.getMap().forEach((key, value) -> {
                System.out.println(key);

                Arrays.stream(value)
                        .map(ImportSuiteTestDto::getTests)
                        .flatMap(tests -> tests.entrySet().stream())
                        .forEach(kvp -> System.out.printf("Name: %s - %s%n", kvp.getKey(), kvp.getValue()));
            });
        }
    }

    @Override
    public YamlDto getYamlDtoFromYamlFile(String filePath) throws IOException {
        File file = this.fileUtil.getFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(file, YamlDto.class);
    }

    @Override
    public YamlDto getYamlDtoFromYamlFile(File file) throws IOException {
        return new ObjectMapper(new YAMLFactory()).readValue(file, YamlDto.class);
    }

    @Override
    public YamlDto getYamlDtoFromYamlFile(URL url) throws IOException {
        return new ObjectMapper(new YAMLFactory()).readValue(url, YamlDto.class);
    }

    @Override
    public YamlDto getYamlDtoFromYamlFile(InputStream inputStream) throws IOException {
        return new ObjectMapper(new YAMLFactory()).readValue(inputStream, YamlDto.class);
    }

    @Override
    public boolean checkYamlCompatibility(File file, Class<?> aClass) throws IOException {
        try {
            new ObjectMapper(new YAMLFactory()).readValue(file, YamlDto.class);
            return true;
        } catch (JsonParseException | JsonMappingException e) {
            return false;
        }

    }

    @Override
    public boolean checkYamlCompatibility(InputStream inputStream, Class<?> aClass) throws IOException {
        try {
            new ObjectMapper(new YAMLFactory()).readValue(inputStream, YamlDto.class);
            return true;
        } catch (JsonParseException | JsonMappingException e) {
            return false;
        }
    }
}
