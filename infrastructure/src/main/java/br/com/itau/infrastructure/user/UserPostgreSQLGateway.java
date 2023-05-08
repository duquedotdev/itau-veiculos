package br.com.itau.infrastructure.user;


import br.com.itau.infrastructure.user.persistence.UserJpaEntity;
import br.com.itau.infrastructure.user.persistence.UserRepository;
import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.itau.infrastructure.utils.SpecificationUtils.like;

@Service
public class UserPostgreSQLGateway implements UserGateway {
  private final UserRepository repository;
  
  public UserPostgreSQLGateway(final UserRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  @Override
  public User create(User aUser) {
    return save(aUser);
  }
  
  private User save(final User aUser) {
    return this.repository.save(UserJpaEntity.from(aUser)).toAggregate();
  }

  @Override
  public void deleteById(UserID anId) {
      final var userId = anId.getValue();
      if(this.repository.existsById(userId)) {
        this.repository.deleteById(userId);
      }
  }

  @Override
  public Optional<User> findById(UserID anId) {
    return this.repository.findById(anId.getValue()).map(UserJpaEntity::toAggregate);
  }

  @Override
  public User update(final User aUser) {
    return save(aUser);
  }

  @Override
  public Pagination<User> findAll(SearchQuery aQuery) {
    // Paginação
    final var page = PageRequest.of(
      aQuery.page(),
      aQuery.perPage(),
      Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
    );

    // Busca dinamica pelo criterio terms (name ou description)
    final var specifications = Optional.ofNullable(aQuery.terms())
      .filter(str -> !str.isBlank())
      .map(this::assembleSpecification)
      .orElse(null);

    final var pageResult =
      this.repository.findAll(Specification.where(specifications), page);

    return new Pagination<>(
      pageResult.getNumber(),
      pageResult.getSize(),
      pageResult.getTotalElements(),
      pageResult.map(UserJpaEntity::toAggregate).toList()
    );
  }

  @Override
  public List<UserID> existsByIds(Iterable<UserID> ids) {
    return null;
  }


  private Specification<UserJpaEntity> assembleSpecification(final String str) {
    final Specification<UserJpaEntity> nameLike = like("name", str);
    final Specification<UserJpaEntity> descriptionLike = like("description", str);
    return nameLike.or(descriptionLike);
  }
  
}
