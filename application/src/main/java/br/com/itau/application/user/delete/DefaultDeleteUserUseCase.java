package br.com.itau.application.user.delete;

import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;

import java.util.Objects;

public class DefaultDeleteUserUseCase extends DeleteUserUseCase {
  
  private final UserGateway userGateway;
  
  public DefaultDeleteUserUseCase(final UserGateway userGateway) {
    this.userGateway = Objects.requireNonNull(userGateway);
  }
  
  @Override
  public void execute(String anIN) {
    this.userGateway.deleteById(UserID.from(anIN));
  }
}
