package com.vmware.utms.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vmware.utms.domain.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test_runs")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class TestRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "run_for_project")
    private long runForProject;

    @NonNull
    @JsonManagedReference
    @OneToMany(mappedBy = "testRun", cascade = CascadeType.PERSIST)
    private Set<TestSuite> testSuites = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    private Project project;

//    public TestRun(Status status, Set<TestSuite> testSuites) {
//        this.status = status;
//        this.testSuites = testSuites;
//    }

}
