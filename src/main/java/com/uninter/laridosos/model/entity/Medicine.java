package com.uninter.laridosos.model.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer concentration;

    public Medicine update(Medicine medicineRequest) {
        if (medicineRequest.getName() != null) {
            this.name = medicineRequest.getName();
        }
        if (medicineRequest.getConcentration() != null) {
            this.concentration = medicineRequest.getConcentration();
        }
        return this;
    }
}
