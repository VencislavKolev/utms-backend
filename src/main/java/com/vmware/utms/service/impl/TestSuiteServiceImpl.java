package com.vmware.utms.service.impl;

import com.vmware.utms.domain.entity.TestSuite;
import com.vmware.utms.domain.repository.TestSuiteRepository;
import com.vmware.utms.service.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSuiteServiceImpl implements TestSuiteService {

    private final TestSuiteRepository testSuiteRepo;

    @Autowired
    public TestSuiteServiceImpl(TestSuiteRepository testSuiteRepo) {
        this.testSuiteRepo = testSuiteRepo;
    }

    @Override
    public void saveSuite(TestSuite testSuite) {
        this.testSuiteRepo.save(testSuite);
    }
}
