package com.uninter.laridosos.model.entity;

import com.uninter.laridosos.enumerator.DosageTypeEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicines_treatment")
public class MedicineTreatment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;

    @Column(nullable = false)
    private Integer dosage;

    @Enumerated(EnumType.STRING)
    @Column(name = "dosage_type", nullable = false)
    private DosageTypeEnum dosageType;

    @Column(name = "hour_interval", nullable = false)
    private Integer hourInterval;

}
