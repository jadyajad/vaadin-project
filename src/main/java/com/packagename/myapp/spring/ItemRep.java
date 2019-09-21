package com.packagename.myapp.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRep extends JpaRepository<Item,Long> {
}
