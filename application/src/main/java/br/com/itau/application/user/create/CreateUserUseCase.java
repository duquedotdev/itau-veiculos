package br.com.itau.application.user.create;

import br.com.itau.application.UseCase;
import br.com.itau.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserUseCase 
  extends UseCase<CreateUserCommand, Either<Notification,CreateUserOutput>> {
}
