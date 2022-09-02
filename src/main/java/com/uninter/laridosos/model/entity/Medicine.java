package com.uninter.laridosos.model.entity;

import com.uninter.laridosos.enumerator.DosageTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String dosage;

    @Enumerated(EnumType.STRING)
    @Column(name = "dosage_type", nullable = false)
    private DosageTypeEnum dosageType;

    @Column(name = "hour_interval", nullable = false)
    private Integer hourInterval;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToMany(mappedBy = "medicines")
    private Set<Treatment> treatments;
}
