package br.com.itau.application.user.retrieve.get;

import br.com.itau.exceptions.NotFoundException;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {
  
  private final UserGateway gateway;
  
  public DefaultGetUserByIdUseCase(final UserGateway gateway) {
    this.gateway = Objects.requireNonNull(gateway);
  }
  
  @Override
  public UserOutput execute(final String anIN) {
    final var aUserID = UserID.from(anIN);    
    return this.gateway.findById(aUserID).map(UserOutput::from).orElseThrow(notFound(aUserID));
  }

  private Supplier<NotFoundException> notFound(final UserID anId) {
    return () -> NotFoundException.with(User.class, anId);
  }
}
