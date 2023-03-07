package com.eduardomatsuda.lojavirtual;


import java.text.SimpleDateFormat;
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
import com.eduardomatsuda.lojavirtual.domain.ItemPedido;
import com.eduardomatsuda.lojavirtual.domain.Pagamento;
import com.eduardomatsuda.lojavirtual.domain.PagamentoComBoleto;
import com.eduardomatsuda.lojavirtual.domain.PagamentoComCartao;
import com.eduardomatsuda.lojavirtual.domain.Pedido;
import com.eduardomatsuda.lojavirtual.domain.Produto;
import com.eduardomatsuda.lojavirtual.domain.enums.EstadoPagamento;
import com.eduardomatsuda.lojavirtual.domain.enums.TipoCliente;
import com.eduardomatsuda.lojavirtual.repositories.CategoriaRepository;
import com.eduardomatsuda.lojavirtual.repositories.CidadeRepository;
import com.eduardomatsuda.lojavirtual.repositories.ClienteRepository;
import com.eduardomatsuda.lojavirtual.repositories.EnderecoRepository;
import com.eduardomatsuda.lojavirtual.repositories.EstadoRepository;
import com.eduardomatsuda.lojavirtual.repositories.ItemPedidoRepository;
import com.eduardomatsuda.lojavirtual.repositories.PagamentoRepository;
import com.eduardomatsuda.lojavirtual.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetolojavirtualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Peças e Acessorios");
		Categoria cat2 = new Categoria(null, "Pneus");
		Categoria cat3 = new Categoria(null, "Escapamentos");
		Categoria cat4 = new Categoria(null, "Caminhões");
		Categoria cat5 = new Categoria(null, "Vans e Mini-Vans");
		Categoria cat6 = new Categoria(null, "Som e Video");
		Categoria cat7 = new Categoria(null, "Multimidias");
		
		
		Produto p1  = new Produto (null, "Auto Radio Original Polo TSI", 2500.00 );
		Produto p2 = new Produto (null, "Pneu 205 55 16", 550.00);
		Produto p3  = new Produto(null, "Lanterna Traseira Esquerda Polo", 259.99 );
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2022 10:35"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2022 19:55"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/11/2022 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2500.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 259.99);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 550.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}

