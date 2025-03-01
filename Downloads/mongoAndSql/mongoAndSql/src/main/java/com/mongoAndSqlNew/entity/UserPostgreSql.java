package com.mongoAndSqlNew.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "user_postgre")
@NamedStoredProcedureQuery(
        name = "insert_user",
        procedureName = "insert_user",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class)
        }
)
public class UserPostgreSql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

