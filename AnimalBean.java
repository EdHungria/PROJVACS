package br.edu.ufra.bean;

import br.edu.ufra.entidade.Animal;
import br.edu.ufra.rn.AnimalRN;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AnimalBean {

    private List<Animal> animais;
    private Animal animal = new Animal();
    private final AnimalRN ANIMAL_RN = new AnimalRN();

    public AnimalBean() {
    }

    public void init() {
        animais = ANIMAL_RN.listar();
    }

    public List<Animal> getAnimais() {
        if (animais == null) {
            init();
        }
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void excluir() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (ANIMAL_RN.excluir(animal)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Animal excluído");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
            animais = ANIMAL_RN.listar();
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível excluir o animal");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

    public void salvar() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (ANIMAL_RN.salvar(animal)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Animal salvo");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
            animais = ANIMAL_RN.listar();
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível salvar o animal");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

    public String getTipoFormatado(Animal animal) {
        if (animal == null) {
            return "";
        }
        return animal.getTipo() == 'C' ? "Cachorro" : "Gato";
    }
}
