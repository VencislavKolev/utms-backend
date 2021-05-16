package com.vmware.utms.domain.dto;

import com.vmware.utms.domain.entity.enums.Status;

public class TestCaseDto {
    private String testName;
    private Status status;

    public TestCaseDto() {
    }

    public TestCaseDto(String testName, Status status) {
        this.testName = testName;
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
