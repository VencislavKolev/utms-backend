package com.vmware.utms.cli.service.impl;

import com.vmware.utms.cli.models.enums.Status;
import com.vmware.utms.cli.models.jsonExport.SuiteDto;
import com.vmware.utms.cli.models.jsonExport.TestDto;
import com.vmware.utms.cli.models.yamlImport.ImportSuiteDto;
import com.vmware.utms.cli.models.yamlImport.YamlDto;
import com.vmware.utms.cli.service.SuiteGenerator;
import com.vmware.utms.cli.service.TestGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SuiteGeneratorImpl implements SuiteGenerator {
    private final TestGenerator testGenerator;

    @Autowired
    public SuiteGeneratorImpl(TestGenerator testGenerator) {
        this.testGenerator = testGenerator;
    }

    @Override
    public List<SuiteDto> getSuites(YamlDto yamlDto, Map<String, String> commands) {
        List<SuiteDto> suites = new ArrayList<>();

        for (ImportSuiteDto suite : yamlDto.getSuites()) {
            suite.getMap().forEach((suiteName, suiteTests) -> {
                List<TestDto> tests = new ArrayList<>();
                try {
                    tests = this.testGenerator.generateTests(suiteTests, suiteName, commands);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Status status = this.getSuiteStatus(tests);
                SuiteDto suiteDto = new SuiteDto(suiteName, status, tests);
                suites.add(suiteDto);
            });

        }
        return suites;

    }

    private Status getSuiteStatus(List<TestDto> tests) {
        boolean hasFailedTest = tests
                .stream().anyMatch(t -> t.getTestDetailDto().getStatus().equals(Status.FAILED));
        if (hasFailedTest) {
            return Status.FAILED;
        }

        boolean allSuitesSkipped = tests
                .stream().allMatch(t -> t.getTestDetailDto().getStatus().equals(Status.SKIPPED));

        if (allSuitesSkipped) {
            return Status.SKIPPED;
        } else {
            return Status.PASSED;
        }
    }
}
