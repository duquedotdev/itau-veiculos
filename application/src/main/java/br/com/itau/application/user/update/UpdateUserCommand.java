package br.com.itau.application.user.update;

import java.time.LocalDate;
import java.util.List;

public record UpdateUserCommand(
  String id,
  String name,
  String email,
  LocalDate birthdate,
  String address,
  List<String> skills,
  Boolean isActive
  
){
  public static UpdateUserCommand with(
    final String id,
    final String name,
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive
  ){
    return new UpdateUserCommand(id, name, email, birthdate, address, skills, isActive);
  }
}
