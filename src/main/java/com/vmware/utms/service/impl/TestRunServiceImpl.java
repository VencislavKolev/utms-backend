package com.vmware.utms.service.impl;

import com.vmware.utms.cli.models.enums.Status;
import com.vmware.utms.cli.models.jsonExport.ReportDto;
import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.domain.entity.TestSuite;
import com.vmware.utms.domain.repository.TestCaseRepository;
import com.vmware.utms.domain.repository.TestRunRepository;
import com.vmware.utms.service.TestRunService;
import com.vmware.utms.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TestRunServiceImpl implements TestRunService {
    private final TestRunRepository testRunRepo;
    private final TestSuiteService testSuiteService;
    private final TestCaseRepository testCaseRepo;

    @Autowired
    public TestRunServiceImpl(TestRunRepository testRunRepo, TestSuiteService testSuiteService, TestCaseRepository testCaseRepo) {
        this.testRunRepo = testRunRepo;
        this.testSuiteService = testSuiteService;
        this.testCaseRepo = testCaseRepo;
    }


    @Override
    public TestRun getRunFromResult(ReportDto reportDto) {
        Set<TestSuite> testSuites = this.testSuiteService.getTestSuites(reportDto.getSuites());
        Status status = reportDto.getStatus();
        return new TestRun(status, testSuites);
    }

    @Override
    public void saveRun(TestRun testRun) {
        this.testRunRepo.save(testRun);
        for (TestSuite testSuite : testRun.getTestSuites()) {
            for (TestCase testCase : testSuite.getTestCases()) {

                testCase.setTestSuite(testSuite);
                this.testCaseRepo.save(testCase);
            }
            testSuite.setTestRun(testRun);
            this.testSuiteService.saveSuite(testSuite);
        }
//
//        for (TestSuite testSuite : testRun.getTestSuites()) {
//            for (TestCase testCase : testSuite.getTestCases()) {
//
//                this.testCaseRepo.save(testCase);
//                testCase.setTestSuite(testSuite);
//            }
//            this.testSuiteService.saveSuite(testSuite);
//            testSuite.setTestRun(testRun);
//        }
    }

}
