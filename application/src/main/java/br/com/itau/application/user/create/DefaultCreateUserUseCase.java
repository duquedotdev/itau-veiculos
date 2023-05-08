package br.com.itau.application.user.create;

import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateUserUseCase extends CreateUserUseCase{
  
  private final UserGateway groupGateway;
  
  public DefaultCreateUserUseCase(final UserGateway groupGateway) {
    this.groupGateway = Objects.requireNonNull(groupGateway);
  }
  
  @Override
  public Either<Notification, CreateUserOutput> execute(CreateUserCommand aCommand) {
    final var name = aCommand.name();
    final var email = aCommand.email();
    final var birthdate = aCommand.birthdate();
    final var address = aCommand.address();
    final var skills = aCommand.skills();
    final var isActive = aCommand.isActive();
    
    final var notification = Notification.create();
    
    final var aUser = User.newUser(name, email, birthdate, address, skills, isActive);
    
    aUser.validate(notification);

    return notification.hasError() ? Left(notification) : create(aUser);
  }

  private Either<Notification, CreateUserOutput> create(final User aGroup) {
    return Try(() -> this.groupGateway.create(aGroup))
      .toEither()
      .bimap(Notification::create, CreateUserOutput::from);
  }
  
  
  
}
