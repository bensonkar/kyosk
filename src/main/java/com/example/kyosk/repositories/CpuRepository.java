package com.example.kyosk.repositories;

import com.example.kyosk.entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface CpuRepository extends JpaRepository<Cpu, Long> {
}
