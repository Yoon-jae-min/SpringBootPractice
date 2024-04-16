package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_id")
    @SequenceGenerator(name = "coffee_id", sequenceName = "coffee_id", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column
    private String price;
}
