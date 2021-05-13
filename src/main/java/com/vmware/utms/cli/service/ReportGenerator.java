package com.vmware.utms.cli.service;

import com.vmware.utms.cli.models.jsonExport.ReportDto;
import com.vmware.utms.cli.models.yamlImport.YamlDto;

import java.util.Map;

public interface ReportGenerator {
    ReportDto getReport(YamlDto yamlDto, String runId, Map<String, String> commands);
}
