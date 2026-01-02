package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Animal;
import java.util.List;

public class AnimalRN {

    private final GenericDAO<Animal> ANIMAL_DAO = new GenericDAO<>();

    public boolean salvar(Animal animal) {
        if (animal == null) {
            return false;
        } else {
            if (animal.getId() == null || animal.getId() == 0) {
                return ANIMAL_DAO.criar(animal);
            } else {
                return ANIMAL_DAO.alterar(animal);
            }
        }
    }

    public List<Animal> listar() {
        return ANIMAL_DAO.obterTodos(Animal.class);
    }

    public Animal obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return ANIMAL_DAO.obter(Animal.class, id);
        }
    }

    public boolean excluir(Animal animal) {
        if (animal == null) {
            return false;
        } else {
            return ANIMAL_DAO.excluir(animal);
        }
    }
}
