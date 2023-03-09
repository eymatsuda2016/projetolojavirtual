package com.eduardomatsuda.lojavirtual.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.eduardomatsuda.lojavirtual.domain.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		@NotEmpty(message= "O campo nome é mandatório")
		@Length(min=5,  max=120, message="O tamanho deve obedecer o critério de min 5 e max 120 caracteres")
		private String nome;
		
		@NotEmpty(message= "O campo e-mail é mandatório")
		@Email(message= "e-mail é iválido")
		private String email;
		
		public ClienteDTO() {
		}
		
		
		public ClienteDTO(Cliente obj) {
			id = obj.getId();
			nome = obj.getNome();
			email = obj.getEmail();
		}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
		
		
}
