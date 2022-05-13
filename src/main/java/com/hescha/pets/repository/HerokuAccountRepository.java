package com.hescha.pets.repository;

import com.hescha.pets.model.HerokuAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerokuAccountRepository extends JpaRepository<HerokuAccount, Long> {
}
