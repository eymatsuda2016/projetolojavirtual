package com.eduardomatsuda.lojavirtual;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.lojavirtual.domain.Categoria;
import com.eduardomatsuda.lojavirtual.domain.Cidade;
import com.eduardomatsuda.lojavirtual.domain.Cliente;
import com.eduardomatsuda.lojavirtual.domain.Endereco;
import com.eduardomatsuda.lojavirtual.domain.Estado;
import com.eduardomatsuda.lojavirtual.domain.Produto;
import com.eduardomatsuda.lojavirtual.domain.enums.TipoCliente;
import com.eduardomatsuda.lojavirtual.repositories.CategoriaRepository;
import com.eduardomatsuda.lojavirtual.repositories.CidadeRepository;
import com.eduardomatsuda.lojavirtual.repositories.ClienteRepository;
import com.eduardomatsuda.lojavirtual.repositories.EnderecoRepository;
import com.eduardomatsuda.lojavirtual.repositories.EstadoRepository;
import com.eduardomatsuda.lojavirtual.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetolojavirtualApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecorepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetolojavirtualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Peças e Acessorios");
		Categoria cat2 = new Categoria(null, "Pneus");
		
		Produto p1  = new Produto (null, "Auto Radio Original Polo TSI", 2500.00 );
		Produto p2 = new Produto (null, "Pneu 205 55 16", 550.00);
		Produto p3  = new Produto(null, "Lanterna Traseira Esquerda Polo", 259.99 );
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade c2 =  new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "MariaSilva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "993838393"));
		
		Endereco e1 = new Endereco(null, "Rua das Flores","300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(e1, e2));
	}
}

