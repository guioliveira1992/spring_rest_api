package com.gms.pontoInteligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gms.pontoInteligente.api.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{

	private static final long serialVersionUID = -1455357585127440456L;
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String senha;
	private BigDecimal valorHora;
	private Float qtdeHorasTrabalhadaDia;
	private Float qtdeHoraAlmoco;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> lancamentoList;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // gerador de contador automatico
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name = "valor_hora", nullable = true)
	public BigDecimal getValorHora() {
		return valorHora;
	}
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}
	
	/*
	 * @Transient é ignorada pelo JPA na manipulação do banco de dados
	 */
	@Transient 
	public Optional<BigDecimal> getValorHoraOpt(){
		return Optional.ofNullable(valorHora);
	}
	
	@Column(name = "qtde_hora_trabalhada_dia", nullable = true)
	public Float getQtdeHorasTrabalhadaDia() {
		return qtdeHorasTrabalhadaDia;
	}
	public void setQtdeHorasTrabalhadaDia(Float qtdeHorasTrabalhadaDia) {
		this.qtdeHorasTrabalhadaDia = qtdeHorasTrabalhadaDia;
	}
	
	/*
	 * @Transient é ignorada pelo JPA na manipulação do banco de dados
	 */
	@Transient 
	public Optional<Float> getHorasTrabalhadaDiaOpt(){
		return Optional.ofNullable(qtdeHorasTrabalhadaDia);
	}
	
	@Column(name = "qtde_hora_almoco", nullable = true)
	public Float getQtdeHoraAlmoco() {
		return qtdeHoraAlmoco;
	}
	public void setQtdeHoraAlmoco(Float qtdeHoraAlmoco) {
		this.qtdeHoraAlmoco = qtdeHoraAlmoco;
	}
	
	/*
	 * @Transient é ignorada pelo JPA na manipulação do banco de dados
	 */
	@Transient 
	public Optional<Float> getHorasAlmocoOpt(){
		return Optional.ofNullable(qtdeHoraAlmoco);
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@Column(name="data_criacao", nullable= false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name="data_atualizacao", nullable= false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	

	/*
	 * FetchType.EAGER -> Garante que os dados da empresa serão carregados assim que o objeto for instanciado
	 * N Funcionario para 1 empresa
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	// mapeando uma collection para uma entity/tabela, FETCH garante que não são todos os funcionários que serão
	// carregados ao instanciar um objeto "empresa" e o Cascade para fazer o efeito "cascatas" nas alterações
	// realizadas na empresa para os funcionários
	// 1 Funcionario para N lançamentos
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentoList() {
		return lancamentoList;
	}
	public void setLancamentoList(List<Lancamento> lancamentoList) {
		this.lancamentoList = lancamentoList;
	}

	
	/*
	 * Garante que a data será atualizada assim que o objeto "empresa" for criado
	 */
	@PrePersist
	public void prePersist() {
		final Date dataAtual = new Date();
		dataCriacao = dataAtual;
		dataAtualizacao = dataAtual;
	}
		
	/*
	 * Garante que a data será atualizada assim que o objeto "empresa" for alterada
	 */
	@PreUpdate
	public void preUpdate() {
		final Date dataAtual = new Date();
		dataAtualizacao = dataAtual;
	}

}
