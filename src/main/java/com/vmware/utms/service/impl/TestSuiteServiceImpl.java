package com.vmware.utms.service.impl;

import com.vmware.utms.cli.models.enums.Status;
import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.entity.TestSuite;
import com.vmware.utms.domain.repository.TestSuiteRepository;
import com.vmware.utms.service.TestCaseService;
import com.vmware.utms.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TestSuiteServiceImpl implements TestSuiteService {

    private final TestSuiteRepository testSuiteRepo;
    private final TestCaseService testCaseService;

    @Autowired
    public TestSuiteServiceImpl(TestSuiteRepository testSuiteRepo, TestCaseService testCaseService) {
        this.testSuiteRepo = testSuiteRepo;
        this.testCaseService = testCaseService;
    }

    @Override
    public Set<TestSuite> getTestSuites(List<SuiteDto> suiteDtos) {
        Set<TestSuite> testSuites = new HashSet<>();
        for (SuiteDto suite : suiteDtos) {
            String suiteName = suite.getName();
            Status status = suite.getStatus();
            Set<TestCase> testCases = this.testCaseService.getTestCases(suite);

            TestSuite testSuite = new TestSuite(suiteName, status, testCases);
            testSuites.add(testSuite);
        }
        return testSuites;
    }

    @Override
    public void saveSuite(TestSuite testSuite) {
        this.testSuiteRepo.save(testSuite);
    }
}
