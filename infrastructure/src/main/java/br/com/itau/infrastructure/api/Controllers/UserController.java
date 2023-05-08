package br.com.itau.infrastructure.api.Controllers;

import br.com.itau.application.user.create.CreateUserCommand;
import br.com.itau.application.user.create.CreateUserOutput;
import br.com.itau.application.user.create.CreateUserUseCase;
import br.com.itau.application.user.delete.DeleteUserUseCase;
import br.com.itau.application.user.retrieve.get.GetUserByIdUseCase;
import br.com.itau.application.user.retrieve.list.ListUsersUseCase;
import br.com.itau.application.user.update.UpdateUserCommand;
import br.com.itau.application.user.update.UpdateUserOutput;
import br.com.itau.application.user.update.UpdateUserUseCase;
import br.com.itau.infrastructure.api.UserAPI;
import br.com.itau.infrastructure.user.models.CreateUserRequest;
import br.com.itau.infrastructure.user.models.UpdateUserRequest;
import br.com.itau.infrastructure.user.models.UserListResponse;
import br.com.itau.infrastructure.user.models.UserResponse;
import br.com.itau.infrastructure.user.presenter.UserApiPresenter;
import br.com.itau.pagination.Pagination;
import br.com.itau.pagination.SearchQuery;
import br.com.itau.validation.handler.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class UserController implements UserAPI {
  
  private final CreateUserUseCase createUserUseCase;
  private final ListUsersUseCase listUsersUseCase;  
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;

  public UserController(
    final CreateUserUseCase createUserUseCase,
    final ListUsersUseCase listUsersUseCase,
    final GetUserByIdUseCase getUserByIdUseCase,
    final UpdateUserUseCase updateUserUseCase,
    final DeleteUserUseCase deleteUserUseCase
  ) {
    this.createUserUseCase = Objects.requireNonNull(createUserUseCase);
    this.listUsersUseCase = Objects.requireNonNull(listUsersUseCase);
    this.getUserByIdUseCase = Objects.requireNonNull(getUserByIdUseCase);
    this.updateUserUseCase = Objects.requireNonNull(updateUserUseCase);
    this.deleteUserUseCase = Objects.requireNonNull(deleteUserUseCase);
  }

  @Override
  public ResponseEntity<?> createUser(CreateUserRequest input) {
    final var aCommand =  CreateUserCommand.with(
      input.name(),
      input.email(),
      input.birthdate(),
      input.address(),
      input.skills(),
      input.isActive() != null ? input.isActive() : true
    );
    
    final Function<Notification, ResponseEntity<?>> onError = notification ->
      ResponseEntity.unprocessableEntity().body(notification);

    final Function<CreateUserOutput, ResponseEntity<?>> onSuccess = output ->
      ResponseEntity.created(URI.create("/users/" + output.id())).body(output);

    return this.createUserUseCase.execute(aCommand)
      .fold(onError, onSuccess);
  }

  @Override
  public Pagination<UserListResponse> listUsers(
    final String search, 
    final int page, 
    final int perPage, 
    final String sort, 
    final String direction) {
    return listUsersUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
      .map(UserApiPresenter::present);
  }

  @Override
  public UserResponse getById(final String id) {
    return UserApiPresenter.present(this.getUserByIdUseCase.execute(id));
  }

  @Override
  public ResponseEntity<?> updateById(String id, UpdateUserRequest input) {
    final var aCommand = UpdateUserCommand.with(
      id,
      input.name(),
      input.email(),
      input.birthdate(),
      input.address(),
      input.skills(),
      input.isActive() != null ? input.isActive() : true
    );
    
    final Function<Notification, ResponseEntity<?>> onError = notification ->
      ResponseEntity.unprocessableEntity().body(notification);
    
    final Function<UpdateUserOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;
    
    return this.updateUserUseCase.execute(aCommand)
      .fold(onError, onSuccess);
  }

  @Override
  public void deleteById(final String id) {
    this.deleteUserUseCase.execute(id);
  }

}
