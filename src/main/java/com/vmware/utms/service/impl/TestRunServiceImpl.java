package com.vmware.utms.service.impl;

import com.vmware.utms.domain.dto.TestCaseDto;
import com.vmware.utms.domain.dto.TestRunDetailDto;
import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.domain.entity.TestSuite;
import com.vmware.utms.domain.entity.enums.Status;
import com.vmware.utms.domain.repository.TestRunRepository;
import com.vmware.utms.service.TestCaseService;
import com.vmware.utms.service.TestRunService;
import com.vmware.utms.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vmware.utms.common.ExceptionMessages.TESTRUN_NOT_FOUND;

@Service
public class TestRunServiceImpl implements TestRunService {
    private final TestRunRepository testRunRepo;
    private final TestSuiteService testSuiteService;
    private final TestCaseService testCaseService;

    @Autowired
    public TestRunServiceImpl(TestRunRepository testRunRepo, TestSuiteService testSuiteService, TestCaseService testCaseService) {
        this.testRunRepo = testRunRepo;
        this.testSuiteService = testSuiteService;
        this.testCaseService = testCaseService;
    }

    @Override
    public void saveRun(TestRun testRun) {
        this.testRunRepo.save(testRun);

        for (TestSuite testSuite : testRun.getTestSuites()) {
            for (TestCase testCase : testSuite.getTestCases()) {
                testCase.setTestSuite(testSuite);
                this.testCaseService.saveTestCase(testCase);
            }
            testSuite.setTestRun(testRun);
            this.testSuiteService.saveSuite(testSuite);
        }
    }

    @Override
    public List<TestRun> getProjectTestRuns(Long projectId) {
        return this.testRunRepo.findAllByProjectIdOrderByRunForProjectDesc(projectId);
    }

    @Override
    public TestRunDetailDto getRunBydId(Long runId, Long projectId) {
        TestRun testRun = this.testRunRepo.findByProjectIdAndRunForProject(projectId, runId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TESTRUN_NOT_FOUND));

        Status status = testRun.getStatus();
        HashMap<String, Set<TestCaseDto>> suites = new HashMap<>();

        testRun.getTestSuites().forEach(s -> {
            String suiteName = s.getName();
            Set<TestCaseDto> caseDtoSet = new HashSet<>();

            s.getTestCases().forEach(tc -> {
                String testName = tc.getName();
                Status testStatus = tc.getStatus();

                TestCaseDto caseDto = new TestCaseDto(testName, testStatus);
                caseDtoSet.add(caseDto);
            });
            suites.put(suiteName, caseDtoSet);
        });

        TestRunDetailDto run = new TestRunDetailDto(status, suites);
        return run;
    }
}
