package com.eduardomatsuda.lojavirtual;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.lojavirtual.domain.Categoria;
import com.eduardomatsuda.lojavirtual.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetolojavirtualApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetolojavirtualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Peças e Acessorios");
		Categoria cat2 = new Categoria(null, "Pneus");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}
}

