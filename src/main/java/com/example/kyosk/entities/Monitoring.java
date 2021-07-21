package com.example.kyosk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "MONITORING")
@Data
public class Monitoring {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private Boolean enabled;
}
