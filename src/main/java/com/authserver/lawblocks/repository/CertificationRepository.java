package com.authserver.lawblocks.repository;

import com.authserver.lawblocks.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Optional<Certification> findFirstByEmailOrderByCreatedDateDesc(String email);

    void deleteByEmail(String email);
}
