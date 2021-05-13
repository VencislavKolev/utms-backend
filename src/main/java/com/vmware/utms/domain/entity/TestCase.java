package com.vmware.utms.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vmware.utms.cli.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    //private boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String output;
    private String error;
    private LocalDateTime start;
    private LocalDateTime end;

    @JsonBackReference
    @ManyToOne
    private TestSuite testSuite;

    public TestCase(String name, String description, Status status, String output, String error, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.output = output;
        this.error = error;
        this.start = start;
        this.end = end;
    }
}
