package com.example.kyosk.repositories;

import com.example.kyosk.entities.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {
}
