package com.eduardomatsuda.lojavirtual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.eduardomatsuda.lojavirtual.domain.Cidade;
import com.eduardomatsuda.lojavirtual.domain.Cliente;
import com.eduardomatsuda.lojavirtual.domain.Endereco;
import com.eduardomatsuda.lojavirtual.domain.enums.TipoCliente;
import com.eduardomatsuda.lojavirtual.dto.ClienteDTO;
import com.eduardomatsuda.lojavirtual.dto.ClienteNewDTO;
import com.eduardomatsuda.lojavirtual.repositories.CidadeRepository;
import com.eduardomatsuda.lojavirtual.repositories.ClienteRepository;
import com.eduardomatsuda.lojavirtual.repositories.EnderecoRepository;
import com.eduardomatsuda.lojavirtual.services.exceptions.DataIntegrityException;
import com.eduardomatsuda.lojavirtual.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível a exclusão de Clientes que tenha entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTo) {
		return new Cliente(objDTo.getId(), objDTo.getNome(), objDTo.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTo) {
		Cliente cli = new Cliente(null, objDTo.getNome(), objDTo.getEmail(), objDTo.getCpfOuCnpj(), TipoCliente.toEnum(objDTo.getTipo()));
		Cidade cid = cidadeRepository.findById(objDTo.getCidadeId()).get();
		Endereco end = new Endereco(null, objDTo.getLogradouro(), objDTo.getNumero(), objDTo.getComplemento(), objDTo.getBairro(), objDTo.getCep(), cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTo.getTelefone1());
		if (objDTo.getTelefone2()!=null) {
			cli.getTelefones().add(objDTo.getTelefone2());
		}
		if (objDTo.getTelefone3()!=null) {
			cli.getTelefones().add(objDTo.getTelefone3());
		}
		return cli;	
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
