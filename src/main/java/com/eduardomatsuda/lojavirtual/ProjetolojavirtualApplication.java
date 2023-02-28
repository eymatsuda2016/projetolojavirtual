package com.eduardomatsuda.lojavirtual;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.lojavirtual.domain.Categoria;
import com.eduardomatsuda.lojavirtual.domain.Produto;
import com.eduardomatsuda.lojavirtual.repositories.CategoriaRepository;
import com.eduardomatsuda.lojavirtual.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetolojavirtualApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

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
	}
}

