package com.example.kyosk.repositories;

import com.example.kyosk.entities.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface LimitsRepository extends JpaRepository<Limits, Long> {
}
