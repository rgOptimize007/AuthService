package com.security.authservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long roleId;

    public String roleName;

    @ManyToMany(mappedBy = "roles")
    public List<User> users;
}
