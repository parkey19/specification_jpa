package com.attacomsian.jpa.domains;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Item implements Serializable {
    @Id
    private Long id;
    private String color;
    private String grade;
    private String name;
}
