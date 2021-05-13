package com.vmware.utms.service;

import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.domain.entity.TestCase;

import java.util.Set;

public interface TestCaseService {
    Set<TestCase> getTestCases(SuiteDto suiteDto);

    void saveTestCase(TestCase testCase);
}
