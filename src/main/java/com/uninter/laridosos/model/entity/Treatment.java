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

    @Column(nullable = false)
    private String disease;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "treatment")
    private Set<MedicineTreatment> medicinesTreatment;

}
