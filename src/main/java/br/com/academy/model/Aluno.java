package br.com.academy.model;

import br.com.academy.enums.Curso;
import br.com.academy.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nome")
    @Size(min = 2, max = 35, message = "O nome deve conter no mínimo 2 caracteres")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    private Curso curso;
    @Column(name = "matricula")
    @NotNull()
    @Size(min = 3, message = "Clique no botão gerar")
    private String matricula;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "turno")
    @Size(min = 3, max = 8, message = "O turno deve conter no mínimo 3 caracteres")
    @NotBlank(message = "O turno não pode ser vazio")
    private String turno;

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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
