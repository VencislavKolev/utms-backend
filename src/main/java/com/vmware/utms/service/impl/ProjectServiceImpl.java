package com.vmware.utms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vmware.utms.cli.service.JsonService;
import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;
import com.vmware.utms.domain.repository.ProjectRepository;
import com.vmware.utms.service.PostJson;
import com.vmware.utms.service.ProjectService;
import com.vmware.utms.service.TestRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.vmware.utms.common.ExceptionMessages.PROJECT_NOT_FOUND;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepo;
    private final TestRunService testRunService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepo, TestRunService testRunService) {
        this.projectRepo = projectRepo;
        this.testRunService = testRunService;
    }

    @Override
    public Long addProject(String projectName, String description) {
        Project project = new Project(projectName, description);
        return this.addProject(project);
    }

    @Override
    public void addRunToProject(TestRun run, Long projectId) {
        Project project = this.projectRepo.findById(projectId).get();
        project.getTestRuns().add(run);
        run.setProject(project);
        this.testRunService.saveRun(run);
        this.projectRepo.save(project);
    }

    @Override
    public Long addProject(Project project) {
        return this.projectRepo.save(project).getId();
    }

    @Override
    public Project getProjectByName(String projectName) {
        return this.projectRepo.getByName(projectName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(PROJECT_NOT_FOUND, projectName)));
    }

    @Override
    public List<Project> getAllProjects() {
        return this.projectRepo.findAll();
    }

    @Override
    public boolean existByName(String name) {
        return this.projectRepo.existsByName(name);
    }

}
