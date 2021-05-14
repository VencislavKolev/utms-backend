package com.vmware.utms.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vmware.utms.domain.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test_suites")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class TestSuite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    @JsonManagedReference
    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.PERSIST)
    private Set<TestCase> testCases = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    private TestRun testRun;

//    public TestSuite(String name, Status status, Set<TestCase> testCases) {
//        this.name = name;
//        this.status = status;
//        this.testCases = testCases;
//    }
}
