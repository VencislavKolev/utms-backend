package com.vmware.utms.controller;

import com.vmware.utms.domain.dto.TestRunDetailDto;
import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.service.ProjectService;
import com.vmware.utms.service.TestRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TestRunService testRunService;

    @Autowired
    public ProjectController(ProjectService projectService, TestRunService testRunService) {
        this.projectService = projectService;
        this.testRunService = testRunService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }

    @GetMapping(path = "/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return this.projectService.getById(projectId);
    }


    @GetMapping(path = "/{projectId}/runs")
    public List<TestRun> getProjectTestRuns(@PathVariable Long projectId) {
        Project project = this.projectService.getById(projectId);
        return this.testRunService.getProjectTestRuns(project.getId());
    }

    @GetMapping(path = "/{projectId}/runs/{runId}")
    public TestRunDetailDto getTestRun(@PathVariable Long projectId, @PathVariable Long runId) {
        Project project = this.projectService.getById(projectId);
        return this.testRunService.getRunBydId(runId, projectId);
    }

}
