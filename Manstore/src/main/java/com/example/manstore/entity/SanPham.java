package com.example.manstore.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SanPham")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "Ma", length = 50)
    private String ma;


    @Column(name = "Ten", length = 100)
    private String ten;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "NgayTao")
    private LocalDate ngayTao;

    @Column(name = "Gia", precision = 18)
    private BigDecimal gia;

//    @Column(name = "GiaSale", precision = 18)
//    private BigDecimal giaSale;


    @Column(name = "MoTa", length = 500)
    private String moTa;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDanhMuc", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private DanhMuc idDanhMuc;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idThuongHieu", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private ThuongHieu idThuongHieu;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCoAo", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private CoAo idCoAo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDuoiAo", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private DuoiAo idDuoiAo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idKieuDang", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private KieuDang idKieuDang;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idChatLieu", nullable = false, referencedColumnName = "id")
//    @JsonManagedReference
    private ChatLieu idChatLieu;

    @Column(name = "TrangThai")
    private Integer trangThai;

//    @OneToMany(mappedBy = "idSanPham", fetch = FetchType.LAZY)
////    @JsonIgnoreProperties({"idSanPham", "hibernateLazyInitializer", "handler"})
//    @JsonIgnore
////    @JsonBackReference
//    private List<ChiTietSanPham> chiTietSanPhams;

    @Column(name = "DuongDan")
    private String DuongDan;

}