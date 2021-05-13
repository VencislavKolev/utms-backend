package com.vmware.utms.cli.service;

import com.vmware.utms.cli.models.jsonExport.TestDetailsInfoDto;
import com.vmware.utms.cli.models.yamlImport.ImportTestDetailDto;

import java.io.IOException;

public interface CommandExecutor {
    TestDetailsInfoDto testParser(ImportTestDetailDto detail) throws InterruptedException, IOException;

    TestDetailsInfoDto getSkippedTest(ImportTestDetailDto detail) throws InterruptedException, IOException;
}
