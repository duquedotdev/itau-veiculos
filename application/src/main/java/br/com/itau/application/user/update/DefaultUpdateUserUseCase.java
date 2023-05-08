package br.com.itau.application.user.update;

import br.com.itau.exceptions.DomainException;
import br.com.itau.exceptions.NotFoundException;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;
import br.com.itau.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateUserUseCase extends UpdateUserUseCase {
  
  private final UserGateway gateway;
  
  public DefaultUpdateUserUseCase(final UserGateway gateway) {
    this.gateway = Objects.requireNonNull(gateway);
  }


  @Override
  public Either<Notification, UpdateUserOutput> execute(final UpdateUserCommand aCommand) {
    final var id = UserID.from(aCommand.id());
    final var name = aCommand.name();
    final var email = aCommand.email();
    final var birthdate = aCommand.birthdate();
    final var address = aCommand.address();
    final var skills = aCommand.skills();
    final var isActive = aCommand.isActive();
    
    final var aUser = this.gateway.findById(id)
      .orElseThrow(notFound(id));
    
    final var notification = Notification.create();
    
    aUser.update(name, email, birthdate, address, skills, isActive).validate(notification);
    
    return notification.hasError() ? Left(notification) : update(aUser);
  }

  private Either<Notification, UpdateUserOutput> update(final User aUser) {
    return Try(() -> this.gateway.update(aUser))
      .toEither()
      .bimap(Notification::create, UpdateUserOutput::from);
  }
  private Supplier<DomainException> notFound(final UserID anId) {
    return () -> NotFoundException.with(User.class, anId);
  }
}
