package com.example.kyosk.repositories;

import com.example.kyosk.entities.MainConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface ConfigRepository extends JpaRepository<MainConfig, Long> {
}
