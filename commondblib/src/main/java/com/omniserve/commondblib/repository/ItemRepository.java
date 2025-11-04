package com.omniserve.commondblib.repository;

import com.omniserve.commondblib.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
    Optional<Item> findByItemId(String itemId);
}
