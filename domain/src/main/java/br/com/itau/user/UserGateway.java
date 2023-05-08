package br.com.itau.user;

import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
  User create(User aUser);

  void deleteById(UserID anId);

  Optional<User> findById(UserID anId);

  User update(User anUser);

  Pagination<User> findAll(SearchQuery aQuery);

  List<UserID> existsByIds(Iterable<UserID> ids);
}
