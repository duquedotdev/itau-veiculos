package br.com.itau.infrastructure.user.presenter;

import br.com.itau.application.user.retrieve.get.UserOutput;
import br.com.itau.application.user.retrieve.list.UsersListOutput;
import br.com.itau.infrastructure.user.models.UserListResponse;
import br.com.itau.infrastructure.user.models.UserResponse;

public interface UserApiPresenter {  
  static UserListResponse present(final UsersListOutput output) {
    return new UserListResponse(
      output.id().getValue(),
      output.name(),
      output.email(),
      output.birthdate(),
      output.address(),
      output.skills(),
      output.isActive(),
      output.createdAt(),
      output.updatedAt(),
      output.deletedAt()
    );
  }

  static UserResponse present(final UserOutput output) {
    return new UserResponse(
      output.id().getValue(),
      output.name(),
      output.email(),
      output.birthdate(),
      output.address(),
      output.skills(),
      output.isActive(),
      output.createdAt(),
      output.updatedAt(),
      output.deletedAt()
    );
  }
  
}
