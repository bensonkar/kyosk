package com.example.kyosk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author bkariuki
 */
@Data
@Entity
@Table(name = "METADATA")
public class Metadata {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @ManyToOne
    private Monitoring monitoring;
}
