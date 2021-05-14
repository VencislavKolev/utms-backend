package com.vmware.utms.service.impl;

import com.vmware.utms.domain.entity.TestCase;
import com.vmware.utms.domain.repository.TestCaseRepository;
import com.vmware.utms.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseServiceImpl implements TestCaseService {
    private final TestCaseRepository testCaseRepo;

    @Autowired
    public TestCaseServiceImpl(TestCaseRepository testCaseRepo) {
        this.testCaseRepo = testCaseRepo;
    }

    @Override
    public void saveTestCase(TestCase testCase) {
        this.testCaseRepo.save(testCase);
    }
}
