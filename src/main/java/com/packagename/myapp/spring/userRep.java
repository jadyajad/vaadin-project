package com.packagename.myapp.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface userRep extends CrudRepository<User, Long> {
    User findByUserName(String username);

    User findById(long id);

}
