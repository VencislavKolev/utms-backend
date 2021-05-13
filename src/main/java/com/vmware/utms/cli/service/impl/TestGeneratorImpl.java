package com.vmware.utms.cli.service.impl;

import com.vmware.utms.cli.models.jsonExport.TestDetailsInfoDto;
import com.vmware.utms.cli.models.jsonExport.TestDto;
import com.vmware.utms.cli.models.yamlImport.ImportSuiteTestDto;
import com.vmware.utms.cli.models.yamlImport.ImportTestDetailDto;
import com.vmware.utms.cli.service.CommandExecutor;
import com.vmware.utms.cli.service.TestGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vmware.utms.common.GlobalConstants.SUITE_CMD;
import static com.vmware.utms.common.GlobalConstants.TEST_CMD;

@Component
public class TestGeneratorImpl implements TestGenerator {
    private final CommandExecutor commandExecutor;

    @Autowired
    public TestGeneratorImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public List<TestDto> generateTests(ImportSuiteTestDto[] suiteTests, String currSuiteName, Map<String, String> commands) throws IOException, InterruptedException {
        List<TestDto> tests = new ArrayList<>();

        Map<String, ImportTestDetailDto> stringTestDetailMap = getMapFromSuiteTests(suiteTests);
        for (Map.Entry<String, ImportTestDetailDto> kvp : stringTestDetailMap.entrySet()) {

            TestDetailsInfoDto currentTest = new TestDetailsInfoDto();
            if (commands.isEmpty()) {
                //no commands -> normal run(everything)
                currentTest = this.commandExecutor.testParser(kvp.getValue());
            } else {
                String suiteNameToRun;
                String testNameToRun;
                if (commands.size() == 1) {
                    if (commands.containsKey(SUITE_CMD)) {
                        //RUN SUITE
                        suiteNameToRun = commands.get(SUITE_CMD);
                        if (suiteNameToRun.equals(currSuiteName)) {
                            currentTest = this.commandExecutor.testParser(kvp.getValue());
                        } else {
                            currentTest = this.commandExecutor.getSkippedTest(kvp.getValue());
                        }
                    } else if (commands.containsKey(TEST_CMD)) {
                        //RUN TEST
                        testNameToRun = commands.get(TEST_CMD);
                        if (testNameToRun.equals(kvp.getKey())) {
                            //IF NAMES MATCH -> RUN TEST and EXPLICITLY SET ENABLED TRUE
                            this.setExplicitlyEnabled(kvp);
                            currentTest = this.commandExecutor.testParser(kvp.getValue());
                        } else {
                            //IF NAMES DIFFER -> GET SKIPPED TEST
                            currentTest = this.commandExecutor.getSkippedTest(kvp.getValue());
                        }
                    }
                } else {
                    suiteNameToRun = commands.get(SUITE_CMD);
                    testNameToRun = commands.get(TEST_CMD);
                    //RUN TEST IN SUITE
                    if (suiteNameToRun.equals(currSuiteName) && testNameToRun.equals(kvp.getKey())) {
                        //IF BOTH MATCH -> RUN TEST and EXPLICITLY SET ENABLED TRUE
                        this.setExplicitlyEnabled(kvp);
                        currentTest = this.commandExecutor.testParser(kvp.getValue());
                    } else {
                        //IF dont match -> skip
                        currentTest = this.commandExecutor.getSkippedTest(kvp.getValue());
                    }
                }
            }
            TestDto testDto = new TestDto(kvp.getKey(), currentTest);
            tests.add(testDto);
        }
        return tests;
    }

    private void setExplicitlyEnabled(Map.Entry<String, ImportTestDetailDto> kvp) {
        kvp.getValue().setEnabled(true);
    }

    private Map<String, ImportTestDetailDto> getMapFromSuiteTests(ImportSuiteTestDto[] suiteTests) {
        return Arrays.stream(suiteTests)
                .map(ImportSuiteTestDto::getTests)
                .flatMap(tests -> tests.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
