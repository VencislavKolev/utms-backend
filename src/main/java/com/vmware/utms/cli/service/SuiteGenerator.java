package com.vmware.utms.cli.service;

import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.cli.models.yamlImport.YamlDto;

import java.util.List;
import java.util.Map;

public interface SuiteGenerator {
    List<SuiteDto> getSuites(YamlDto yamlDto, Map<String, String> commands);
}
