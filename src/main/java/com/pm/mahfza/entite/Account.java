package com.pm.mahfza.entite;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_at")
    private LocalDate createdDate;

    @Column(name = "updated_at")
    private LocalDate updatedDate;

//    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,orphanRemoval = true)
//    @ToString.Exclude
//    private List<Transaction> transactions;

    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedDate = LocalDate.now();
    }

}
