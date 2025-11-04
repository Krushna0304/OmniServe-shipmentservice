package com.omniserve.commondblib.repository;

import com.omniserve.commondblib.entity.SupplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyInfoRepository extends JpaRepository<SupplyInfo,String> {
}
