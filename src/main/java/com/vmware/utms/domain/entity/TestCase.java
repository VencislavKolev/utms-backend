package com.vmware.utms.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vmware.utms.domain.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_cases")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    private String output;

    @NonNull
    private String error;

    @NonNull
    private LocalDateTime start;

    @NonNull
    private LocalDateTime end;

    @JsonBackReference
    @ManyToOne
    private TestSuite testSuite;
}
