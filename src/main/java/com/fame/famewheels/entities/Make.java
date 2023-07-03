package com.fame.famewheels.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Make {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int makeId;
    @Column (name="make", nullable=false, length= 50)
    private String makeName;
    
    @OneToMany(mappedBy="make", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Post> post=new ArrayList<>();
    
    @OneToMany(mappedBy="make", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Model> mode= new ArrayList<>();

    
}
