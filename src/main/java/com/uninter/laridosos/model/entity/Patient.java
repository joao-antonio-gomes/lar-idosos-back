package com.uninter.laridosos.model.entity;

import com.uninter.laridosos.enumerator.GenderEnum;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;
    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean active;


    @ManyToMany(mappedBy = "patients")
    private Set<Responsible> responsibles;

    public Patient update(Patient patientRequest) {
        if (patientRequest.getName() != null) {
            this.name = patientRequest.getName();
        }
        if (patientRequest.getCpf() != null) {
            this.cpf = patientRequest.getCpf();
        }
        if (patientRequest.getBirthDate() != null) {
            this.birthDate = patientRequest.getBirthDate();
        }
        if (patientRequest.getPhone() != null) {
            this.phone = patientRequest.getPhone();
        }
        return this;
    }

    public void removeCpfMask() {
        if (getCpf() == null)
            return;

        setCpf(getCpf().replaceAll("[^0-9]", ""));
    }
}
