package com.vmware.utms.service;

import com.vmware.utms.domain.entity.Project;
import com.vmware.utms.domain.entity.TestRun;

import java.util.List;

public interface ProjectService {
    Long addProject(Project project);

    Project getProjectByName(String name);

    boolean existByName(String name);

    List<Project> getAllProjects();

    Long addProject(String projectName, String description);

    void addRunToProject(TestRun run, Long projectId);
}
