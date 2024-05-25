package com.tobeto.rentacar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
}
