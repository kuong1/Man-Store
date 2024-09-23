package com.example.manstore.repository;

import com.example.manstore.dto.respone.MauSacResponse;
import com.example.manstore.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {

    @Query("SELECT new com.example.manstore.dto.respone.MauSacResponse(ms.id, ms.ma, ms.ten) FROM MauSac ms")
    public Page<MauSacResponse> findAllMauSac(Pageable pageable);

    @Query("SELECT ms FROM MauSac ms WHERE LOWER(ms.ten) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<MauSac> searchMauSac(@Param("keyword") String keyword, Pageable pageable);
}
