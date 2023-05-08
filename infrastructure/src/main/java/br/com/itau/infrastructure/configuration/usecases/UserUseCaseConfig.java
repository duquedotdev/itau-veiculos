package br.com.itau.infrastructure.configuration.usecases;


import br.com.itau.application.user.create.CreateUserUseCase;
import br.com.itau.application.user.create.DefaultCreateUserUseCase;
import br.com.itau.application.user.delete.DefaultDeleteUserUseCase;
import br.com.itau.application.user.delete.DeleteUserUseCase;
import br.com.itau.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import br.com.itau.application.user.retrieve.get.GetUserByIdUseCase;
import br.com.itau.application.user.retrieve.list.DefaultListUsersUseCase;
import br.com.itau.application.user.retrieve.list.ListUsersUseCase;
import br.com.itau.application.user.update.DefaultUpdateUserUseCase;
import br.com.itau.application.user.update.UpdateUserUseCase;
import br.com.itau.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UserUseCaseConfig {
  
  private final UserGateway userGateway;
  
  public UserUseCaseConfig(final UserGateway userGateway) {
    this.userGateway = Objects.requireNonNull(userGateway);
  }
  
  @Bean
  public CreateUserUseCase createGroupUseCase() {
    return new DefaultCreateUserUseCase(userGateway);
  }
  
  @Bean
  public ListUsersUseCase listGroupsUseCase() {
    return new DefaultListUsersUseCase(userGateway);
  }
  
  @Bean
  public GetUserByIdUseCase getUserByIdUseCase() {
    return new DefaultGetUserByIdUseCase(userGateway);
  }
  
  @Bean
  public UpdateUserUseCase updateUserUseCase() { return new DefaultUpdateUserUseCase(userGateway); }
  
  @Bean
  public DeleteUserUseCase deleteUserUseCase() {
    return new DefaultDeleteUserUseCase(userGateway);
  }
}
