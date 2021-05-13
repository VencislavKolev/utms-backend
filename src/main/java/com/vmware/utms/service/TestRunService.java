package com.vmware.utms.service;

import com.vmware.utms.cli.models.jsonExport.ReportDto;
import com.vmware.utms.domain.entity.TestRun;

import java.util.List;

public interface TestRunService {
    TestRun getRunFromResult(ReportDto reportDto);

    void saveRun(TestRun testRun);
}
