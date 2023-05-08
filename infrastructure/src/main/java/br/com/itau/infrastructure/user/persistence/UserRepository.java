package br.com.itau.infrastructure.user.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
  Page<UserJpaEntity> findAll(Specification<UserJpaEntity> whereClause, Pageable page);
}
