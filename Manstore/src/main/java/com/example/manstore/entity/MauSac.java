package com.example.manstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "MauSac")
public class MauSac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "Ma", length = 50)
    private String ma;


    @Column(name = "Ten", length = 100)
    private String ten;

//    @OneToMany(mappedBy = "idMauSac", fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JsonIgnoreProperties({"idMauSac", "hibernateLazyInitializer", "handler"})
//    private List<ChiTietSanPham> chiTietSanPhams;

}