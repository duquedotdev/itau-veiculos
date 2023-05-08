package br.com.itau.application.user.retrieve.list;

import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;
import br.com.itau.user.UserGateway;

import java.util.Objects;

public class DefaultListUsersUseCase extends ListUsersUseCase {
  private final UserGateway gateway;
  
  public DefaultListUsersUseCase(final UserGateway gateway) {
    this.gateway = Objects.requireNonNull(gateway);
  }
  
  @Override
  public Pagination<UsersListOutput> execute(SearchQuery aQuery) {
    return this
            .gateway
            .findAll(aQuery)
            .map(UsersListOutput::from);
  }
}
