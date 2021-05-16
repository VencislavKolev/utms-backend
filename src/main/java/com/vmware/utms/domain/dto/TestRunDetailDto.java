package com.vmware.utms.domain.dto;

import com.vmware.utms.domain.entity.enums.Status;

import java.util.Map;
import java.util.Set;

public class TestRunDetailDto {

    private Status status;
    private Map<String, Set<TestCaseDto>> suites;

    public TestRunDetailDto() {
    }

    public TestRunDetailDto(Status status, Map<String, Set<TestCaseDto>> suites) {
        this.status = status;
        this.suites = suites;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, Set<TestCaseDto>> getSuites() {
        return suites;
    }

    public void setSuites(Map<String, Set<TestCaseDto>> suites) {
        this.suites = suites;
    }
}
