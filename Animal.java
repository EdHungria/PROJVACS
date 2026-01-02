package br.edu.ufra.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
        @NamedQuery(name = "Animal.findById", query = "SELECT a FROM Animal a WHERE a.id = :id")
})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String dono;

    @Column(nullable = false, length = 9)
    private String telefone;

    /**
     * USAR Character (wrapper) — NÃO char
     * C = Cachorro
     * G = Gato
     */
    @Column(nullable = true, length = 1)
    private Character tipo;

    @Temporal(TemporalType.DATE)
    private Date nascimento;

    /**
     * Relacionamento opcional (temporariamente)
     */
    // @ManyToOne
    // @JoinColumn(name = "raca", nullable = true)
    // private Raca raca;

    // ======================
    // CONSTRUTOR
    // ======================
    public Animal() {
    }

    // ======================
    // GETTERS E SETTERS
    // ======================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    // public Raca getRaca() {
    //    return raca;
    //}

     //public void setRaca(Raca raca) {
     //    this.raca = raca;
    //}

    // ======================
    // EQUALS / HASHCODE
    // ======================
    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Animal other = (Animal) object;
        return id != null && id.equals(other.id);
    }

    @Override
    public String toString() {
        return nome;
    }
}
