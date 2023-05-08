package br.com.itau.application.user.update;

import br.com.itau.application.UseCase;
import br.com.itau.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {
}
