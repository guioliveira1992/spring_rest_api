package com.gms.pontoInteligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.gms.pontoInteligente.api.enums.TipoEnum;


@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -801911507014418513L;

	
	private Long id;
	private String descricao;
	private String localizacao;
	private TipoEnum tipo;
	private Funcionario funcionario;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "localizacao", nullable = false)
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	public TipoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
	
	/*
	 * FetchType.EAGER -> Garante que os dados do funcionário serão carregados assim que o objeto for instanciado
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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
