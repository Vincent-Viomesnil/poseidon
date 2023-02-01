package com.nnk.springboot.domain;

import com.nnk.springboot.config.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @ValidPassword
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

}
