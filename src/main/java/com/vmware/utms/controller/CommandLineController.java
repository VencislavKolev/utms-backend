package com.vmware.utms.controller;

import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.service.ProjectService;
import com.vmware.utms.service.TestRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{projectId}/runs")
    public List<TestRun> getProjectTestRuns(@PathVariable Long projectId) {
        Project project = this.projectService.getById(projectId);
        return this.testRunService.getProjectTestRuns(project.getId());
    }

    @PostMapping
    public Long registerProject(@RequestBody Project project) {
        return this.projectService.addProject(project);
    }

    @PostMapping(path = "/{projectId}/runs")
    public void addRunToProject(@PathVariable Long projectId, @RequestBody TestRun testRun) {
        this.projectService.addRunToProject(testRun, projectId);
    }
}
