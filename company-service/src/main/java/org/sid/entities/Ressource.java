package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Entity 
public class Ressource implements Serializable {
    @Id
    @GeneratedValue
    private Long numero;
    private String nom;
    private Date dateAchat;
    private double prix;
    @Min(0)
    @Max(20)
    private double note; 
}