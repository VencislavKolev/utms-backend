package com.vmware.utms.service.impl;

import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.domain.entity.TestSuite;
import com.vmware.utms.domain.repository.TestCaseRepository;
import com.vmware.utms.domain.repository.TestRunRepository;
import com.vmware.utms.service.TestCaseService;
import com.vmware.utms.service.TestRunService;
import com.vmware.utms.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRunServiceImpl implements TestRunService {
    private final TestRunRepository testRunRepo;
    private final TestSuiteService testSuiteService;
    // private final TestCaseRepository testCaseRepo;
    private final TestCaseService testCaseService;

    @Autowired
    public TestRunServiceImpl(TestRunRepository testRunRepo, TestSuiteService testSuiteService,/* TestCaseRepository testCaseRepo,*/ TestCaseService testCaseService) {
        this.testRunRepo = testRunRepo;
        this.testSuiteService = testSuiteService;
        //this.testCaseRepo = testCaseRepo;
        this.testCaseService = testCaseService;
    }

    @Override
    public void saveRun(TestRun testRun) {
        this.testRunRepo.save(testRun);

        for (TestSuite testSuite : testRun.getTestSuites()) {
            for (TestCase testCase : testSuite.getTestCases()) {

                testCase.setTestSuite(testSuite);
                //  this.testCaseRepo.save(testCase);
                this.testCaseService.saveTestCase(testCase);
            }
            testSuite.setTestRun(testRun);
            this.testSuiteService.saveSuite(testSuite);
        }
    }

    @Override
    public List<TestRun> getProjectTestRuns(Long projectId) {
        return this.testRunRepo.findAllByProjectId(projectId);
    }
}
