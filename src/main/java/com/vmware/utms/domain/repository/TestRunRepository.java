package com.vmware.utms.domain.repository;

import com.vmware.utms.domain.entity.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRunRepository extends JpaRepository<TestRun, Long> {

    List<TestRun> findAllByProjectId(Long projectId);

    List<TestRun> findAllByProjectIdOrderByRunForProjectDesc(Long projectId);

    Optional<TestRun> findByProjectIdAndRunForProject(Long projectId, Long runId);
}
