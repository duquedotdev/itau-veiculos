package br.com.itau.application.user.retrieve.list;

import br.com.itau.application.UseCase;
import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;

public abstract class ListUsersUseCase extends UseCase<SearchQuery, Pagination<UsersListOutput>> {
}
