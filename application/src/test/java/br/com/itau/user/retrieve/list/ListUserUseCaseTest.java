package br.com.itau.user.retrieve.list;

import br.com.itau.UseCaseTest;
import br.com.itau.application.user.retrieve.list.DefaultListUsersUseCase;
import br.com.itau.application.user.retrieve.list.UsersListOutput;
import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ListUserUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultListUsersUseCase useCase;

  @Mock
  private UserGateway gateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(gateway);
  }

  @Test
  public void givenAValidQuery_whenCallsListUsers_thenShouldReturnUsers(){
    final var users = 
      List.of(
        User.newUser("Felipe Duque", "felipe@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true),
        User.newUser("Felipe Duque", "felipe@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true));


    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var aQuery =
      new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    final var expectedPagination =
      new Pagination<>(expectedPage, expectedPerPage, users.size(), users);

    final var expectedItemsCount = 2;
    final var expectedResult = expectedPagination.map(UsersListOutput::from);

    when(gateway.findAll(eq(aQuery)))
      .thenReturn(expectedPagination);

    final var actualResult = useCase.execute(aQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedResult, actualResult);
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(users.size(), actualResult.total());
  }


  @Test
  public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyUsers(){
    final var users = List.<User>of();


    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var aQuery =
      new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    final var expectedPagination =
      new Pagination<>(expectedPage, expectedPerPage, users.size(), users);

    final var expectedItemsCount = 0;
    final var expectedResult = expectedPagination.map(UsersListOutput::from);

    when(gateway.findAll(eq(aQuery)))
      .thenReturn(expectedPagination);

    final var actualResult = useCase.execute(aQuery);

    Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
    Assertions.assertEquals(expectedResult, actualResult);
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(users.size(), actualResult.total());
  }

  @Test
  public void givenAValidQuery_whenGatewayThrowsException_shouldReturnException() {
    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";
    final var expectedErrorMessage = "Gateway error";

    final var aQuery =
      new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

    when(gateway.findAll(eq(aQuery)))
      .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var actualException =
      Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
  }

}
