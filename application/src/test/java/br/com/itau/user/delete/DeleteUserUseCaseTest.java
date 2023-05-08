package br.com.itau.user.delete;

import br.com.itau.UseCaseTest;
import br.com.itau.application.user.delete.DefaultDeleteUserUseCase;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteUserUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultDeleteUserUseCase useCase;

  @Mock
  private UserGateway gateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(gateway);
  }

  @Test
  public void givenAValidId_whenCallsDeleteUser_shouldBeOK(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var aUser = User.newUser(name, email, birthdate, address, skills, isActive);

    final var expectedId = aUser.getId();

    doNothing().when(gateway).deleteById(eq(expectedId));

    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(gateway, times(1)).deleteById(eq(expectedId));
  }

  @Test
  public void givenAInvalidId_whenCallsDeleteUser_shouldBeOK() {
    final var expectedId = UserID.from("123");
    doNothing().when(gateway).deleteById(eq(expectedId));
    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));
    Mockito.verify(gateway, times(1)).deleteById(eq(expectedId));
  }

  @Test
  public void givenAValidUserId_whenGatewayThrowsException_shouldReturnException() {
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "Gateway error";

    final var aUser = User.newUser(name, email, birthdate, address, skills, isActive);

    final var expectedId = aUser.getId();

    doThrow(new IllegalStateException(expectedErrorMessage))
      .when(gateway).deleteById(eq(expectedId));

    Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

    Mockito.verify(gateway, times(1)).deleteById(eq(expectedId));
  }

}
