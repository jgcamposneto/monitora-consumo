package br.com.fiap.postech.monitoraconsumo.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private LocalDate dataDeNascimento;
    private Sexo sexo;
    private Parentesco parentesco;
    @ManyToMany
    @JoinTable(name = "tb_relacionamentos",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_relacionada_id"))
    private List<Pessoa> relacionamentos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "endereco.id")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "usuario.id")
    private Usuario usuario;

    public Pessoa setId(UUID id) {
        this.id = id;
        return this;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Pessoa setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public Pessoa setSexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public Pessoa setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
        return this;
    }

    public Pessoa setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public Pessoa setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }
    public Pessoa setRelacionamentos(List<Pessoa> relacionamentos) {
        this.relacionamentos = relacionamentos;
        return this;
    }

    public void adicionarRelacionamento(Pessoa pessoaRelacionada, Parentesco parentesco) {
        switch(parentesco) {
            case PAI:
                this.relacionamentos.add(pessoaRelacionada);
                pessoaRelacionada.setParentesco(Parentesco.FILHO);
                pessoaRelacionada.getRelacionamentos().add(this);
                break;
            case MAE:
                this.relacionamentos.add(pessoaRelacionada);
                pessoaRelacionada.setParentesco(Parentesco.FILHO);
                pessoaRelacionada.getRelacionamentos().add(this);
                break;
            case IRMAO:
                this.relacionamentos.add(pessoaRelacionada);
                pessoaRelacionada.getRelacionamentos().add(this);
                break;
            case FILHO:
                if(pessoaRelacionada.getSexo() == Sexo.MASCULINO) {
                    pessoaRelacionada.setParentesco(Parentesco.PAI);
                } else {
                    pessoaRelacionada.setParentesco(Parentesco.MAE);
                }
                pessoaRelacionada.getRelacionamentos().add(this);
                break;
            default:
                throw new IllegalArgumentException("Parentesco não reconhecido");
        }
    }

}
