package org.laurieandthegang.parkshark.domain.division;

import javax.persistence.*;

@Entity
@Table(name = "DIVISION")
public class Division {
    @Id
    @SequenceGenerator(name = "DIVISION_SEQ", sequenceName = "division_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQ")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "original_name" , nullable = false)
    private String originalName;

    @Column(name = "director", nullable = false)
    private String director;

    @ManyToOne
    @JoinColumn(name = "fk_parent_division")
    private Division parentDivision;

    public Division(String name, String originalName, String director, Division parentDivision) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivision = parentDivision;
    }

    public Division() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirector() {
        return director;
    }

    public Division getParentDivision() {
        return parentDivision;
    }
}
