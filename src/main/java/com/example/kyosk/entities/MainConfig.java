package com.example.kyosk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "CONFIGS")
@Data
public class MainConfig {
    @Id
    @GeneratedValue()
    @JsonIgnore
    private Long id;
    private String name;
    @ManyToOne
    private Metadata metadata;
    @ManyToOne
    private Limits limits;
}
