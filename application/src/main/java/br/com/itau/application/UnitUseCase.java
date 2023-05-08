package br.com.itau.application;

public abstract class UnitUseCase<IN> {
  public abstract void execute(IN anIN);
}
