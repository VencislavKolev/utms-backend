package com.vmware.utms.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name", unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(name = "description")
    @NonNull
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<TestRun> testRuns = new HashSet<>();
}
