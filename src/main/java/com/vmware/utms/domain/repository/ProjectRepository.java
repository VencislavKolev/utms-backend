package com.vmware.utms.domain.repository;

import com.vmware.utms.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String projectName);

    Optional<Project> getByName(String projectName);
}
