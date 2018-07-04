package com.gms.pontoInteligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 8315870052479799693L;
	
	private Long id;
	private String cnpj;
	private String razaoSocial;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> funcionarioList;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // gerador de contador automatico
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="cnpj", nullable= false) //campo obrigatório
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Column(name="razao_social", nullable= false)
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
	
	// mapeando uma collection para uma entity/tabela, FETCH garante que não são todos os funcionários que serão
	// carregados ao instanciar um objeto "empresa" e o Cascade para fazer o efeito "cascatas" nas alterações
	// realizadas na empresa para os funcionários
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
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
