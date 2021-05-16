package com.vmware.utms.service;

import com.vmware.utms.domain.dto.TestRunDetailDto;
import com.vmware.utms.domain.entity.TestRun;

import java.util.List;

public interface TestRunService {
    void saveRun(TestRun testRun);

    List<TestRun> getProjectTestRuns(Long projectId);

    TestRunDetailDto getRunBydId(Long runId, Long projectId);
}
