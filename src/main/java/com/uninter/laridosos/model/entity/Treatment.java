package com.uninter.laridosos.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Treatment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "begin_date", nullable = false)
    private LocalDate beginDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "id", nullable = false)
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @ManyToMany
    @JoinTable(name = "treatment_medicine",
            joinColumns = @JoinColumn(name = "treatment_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicine> medicines;

}
