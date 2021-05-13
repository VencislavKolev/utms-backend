package com.vmware.utms.controller;

import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.service.ProjectService;
import com.vmware.utms.service.TestRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/projects")
public class CommandLineController {
    private final ProjectService projectService;
    private final TestRunService testRunService;

    @Autowired
    public CommandLineController(ProjectService projectService, TestRunService testRunService) {
        this.projectService = projectService;
        this.testRunService = testRunService;
    }

    @GetMapping(path = "/{projectName}")
    public Project getProjectByName(@PathVariable String projectName) {
        return this.projectService.getProjectByName(projectName);
    }

    @PostMapping
    public Long registerProject(@RequestBody Project project) {
        return this.projectService.addProject(project);
    }

//    @PostMapping(path = "/{projectName}/runs")
//    public void addRunToProject(@PathVariable String projectName, @RequestBody TestRun testRun) {
//        Project project = this.projectService.getProjectByName(projectName);
//        this.projectService.addRunToProject(testRun, project.getId());
//    }

    @PostMapping(path = "/{runId}/runs")
    public void addRunToProject(@PathVariable Long runId, @RequestBody TestRun testRun) {
        // Project project = this.projectService.getProjectByName(projectName);
        this.projectService.addRunToProject(testRun, runId);
    }
}
