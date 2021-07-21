package com.example.kyosk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author bkariuki
 */
@Data
@Entity
@Table(name = "LIMITS")
public class Limits {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @ManyToOne
    private Cpu cpu;
}
