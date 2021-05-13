package com.vmware.utms.cli.service;

import com.vmware.utms.cli.models.jsonExport.TestDto;
import com.vmware.utms.cli.models.yamlImport.ImportSuiteTestDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TestGenerator {
    List<TestDto> generateTests(ImportSuiteTestDto[] suiteTests, String currSuiteName, Map<String, String> commands) throws IOException, InterruptedException;
}
