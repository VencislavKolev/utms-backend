//package com.vmware.utms;
//
//import com.vmware.utms.cli.models.jsonExport.ReportDto;
//import com.vmware.utms.cli.service.JsonService;
//import com.vmware.utms.domain.entity.TestRun;
//import com.vmware.utms.service.HttpClientService;
//import com.vmware.utms.service.TestRunService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class Runner implements CommandLineRunner {
//
//    private final JsonService jsonService;
//    private final HttpClientService httpClientService;
//    private final TestRunService testRunService;
//
//    @Autowired
//    public Runner(JsonService jsonService, HttpClientService httpClientService, TestRunService testRunService) {
//        this.jsonService = jsonService;
//        this.httpClientService = httpClientService;
//        this.testRunService = testRunService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            //ReportDto object = this.jsonService.processInput(args);
//
//            //-------------------
////            ReportDto object = this.jsonService.processInput("--config", "testing.yaml", "--run-id", "1");
////            ReportDto object = this.jsonService.processInput("--config", "skipping.yaml");
//
//            //--------------BONUS ARGUMENTS---------
//            //ReportDto object = this.jsonService.processInput("--config", "testing.yaml", "--suite-name", "UITests");
//
//            //ReportDto object = this.jsonService.processInput("--config", "testing.yaml", "--suite-name", "UITests", "--test-name", "Test4");
//            //ReportDto object = this.jsonService.processInput("--config", "testing.yaml", "--suite-name", "UITests", "--test-name", "Test1");
//            //ReportDto object = this.jsonService.processInput("--config", "testing.yaml", "--test-name", "Test4");
//
//            //-------------------SHORT COMMANDS-------------
//            //ReportDto object = this.jsonService.processInput("-c", "testing.yaml", "-sn", "UITests", "-tn", "Test4");
//            //ReportDto object = this.jsonService.processInput("-c", "testing.yaml", "-sn", "UITests", "-tn", "Test8");
//            //ReportDto object = this.jsonService.processInput("-c", "testing.yaml");
//            //this.jsonService.printJsonString(object);
//
//        /*    if (object.getError() == null) {
//                String projectJson = this.jsonService.getJsonString(object.getProject());
//                String id = this.httpClientService.sendRequest(projectJson);
//
//                TestRun run = this.testRunService.getRunFromResult(object);
//                String runJson = this.jsonService.getJsonString(run);
//                this.jsonService.printJsonString(run);
//
//                this.httpClientService.sendRunToProject(runJson, id);
//            }*/
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //System.exit(0);
//        for (String arg : args) {
//            System.out.println(arg);
//        }
//    }
//}
