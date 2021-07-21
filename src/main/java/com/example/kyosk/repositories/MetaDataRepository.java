package com.example.kyosk.repositories;

import com.example.kyosk.entities.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface MetaDataRepository extends JpaRepository<Metadata, Long> {
}
