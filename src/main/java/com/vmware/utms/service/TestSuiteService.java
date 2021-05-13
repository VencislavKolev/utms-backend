package com.vmware.utms.service;

import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.domain.entity.TestSuite;

import java.util.List;
import java.util.Set;

public interface TestSuiteService {
    Set<TestSuite> getTestSuites(List<SuiteDto> suiteDtos);

    void saveSuite(TestSuite testSuite);
}
