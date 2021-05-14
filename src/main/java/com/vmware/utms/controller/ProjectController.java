package com.vmware.utms.controller;

import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.service.ProjectService;
import com.vmware.utms.service.TestRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/projects")
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

    @GetMapping(path = "/{projectId}/runs")
    public List<TestRun> getProjectTestRuns(@PathVariable Long projectId) {
        Project project = this.projectService.getById(projectId);
        return this.testRunService.getProjectTestRuns(project.getId());
    }

    //TODO /projects/:project_id/runs/:run_id GET
}
