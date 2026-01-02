package br.edu.ufra.dao;

import java.util.List;

public interface InterfaceDAO<T> {

    boolean criar(T o);

    boolean alterar(T o);

    boolean excluir(T o);

    T obter(Class<T> classe, Object id);

    List<T> obterTodos(Class<T> classe);

}
