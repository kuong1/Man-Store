package com.example.manstore.repository;

import com.example.manstore.entity.DotGiamGia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia,Integer> {

    @PersistenceContext
    EntityManager entityManager = null;

    @Query("SELECT new com.example.manstore.dto.respone.DotGiamGiaResponse(dgg.id, dgg.ma, dgg.ten, dgg.ngayTao, dgg.ngayBatDau, dgg.ngayKetThuc, dgg.loaiGiamGia, dgg.giaTriGiam, dgg.giaTriDonHang,dgg.trangThai) FROM DotGiamGia dgg")
    public Page<DotGiamGia> findAllDGG(LocalDate start, LocalDate end, String promotionType, Pageable pageable);

    @Query("select v from DotGiamGia v where v.ngayBatDau >= ?1 and v.ngayKetThuc <= ?2")
    Page<DotGiamGia> findByDate(LocalDate start, LocalDate end, Pageable pageable);

    @Query("select v from DotGiamGia v where v.loaiGiamGia = ?1")
    Page<DotGiamGia> findByPromotionType(boolean type, Pageable pageable);

    @Query("select v from DotGiamGia v where  v.ngayBatDau >= ?1 and v.ngayKetThuc <= ?2 and v.loaiGiamGia = ?3")
    Page<DotGiamGia> findByDateAndPromotionType(LocalDate start, LocalDate end,boolean type, Pageable pageable);

    @Query("select v from DotGiamGia v where v.ngayBatDau >= ?1 and v.ngayKetThuc <= ?2 and v.loaiGiamGia = ?3")
    Page<DotGiamGia> findByAll(LocalDate start, LocalDate end, boolean typePromotion, Pageable pageable);

    @Query("select v from DotGiamGia v where v.ten = ?1 and v.id <> ?2")
    Optional<DotGiamGia> findByNameAndId(String code, Integer id);

    @Query("select v from DotGiamGia v where v.ten = ?1 ")
    Optional<DotGiamGia> findByName(String name);

    @Query("SELECT v FROM DotGiamGia v WHERE v.ngayBatDau <= :now AND v.ngayKetThuc >= :now AND v.trangThai = :active")
    List<DotGiamGia> getPromotionAll(LocalDate now, Boolean active);

    @Query("select km from DotGiamGia km inner join KhachHang kh where kh.id = ?1 " +
            "and (?2 >= km.ngayBatDau) and (?2 <= km.ngayKetThuc) and km.trangThai = ?3")
    List<DotGiamGia> getByCustomer(Integer id, LocalDate now,boolean status);

}
