package com.vmware.utms.service.impl;

import com.vmware.utms.cli.models.enums.Status;
import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.cli.models.jsonExport.TestDto;
import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.repository.TestCaseRepository;
import com.vmware.utms.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class TestCaseServiceImpl implements TestCaseService {
    private final TestCaseRepository testCaseRepo;

    @Autowired
    public TestCaseServiceImpl(TestCaseRepository testCaseRepo) {
        this.testCaseRepo = testCaseRepo;
    }

    @Override
    public Set<TestCase> getTestCases(SuiteDto suiteDto) {
        Set<TestCase> testCases = new HashSet<>();
        for (TestDto test : suiteDto.getTests()) {
            String name = test.getName();
            String description = test.getTestDetailDto().getDescription();
            Status status = test.getTestDetailDto().getStatus();
            String output = test.getTestDetailDto().getOutput();
            String error = test.getTestDetailDto().getError();
            LocalDateTime startDate = test.getTestDetailDto().getStartDate();
            LocalDateTime endDate = test.getTestDetailDto().getEndDate();

            TestCase testCase = new TestCase(name, description, status, output, error, startDate, endDate);
            testCases.add(testCase);
        }
        return testCases;
    }

    @Override
    public void saveTestCase(TestCase testCase) {
        this.testCaseRepo.save(testCase);
    }
}
