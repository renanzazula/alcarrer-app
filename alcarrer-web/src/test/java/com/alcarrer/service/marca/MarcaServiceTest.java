package com.alcarrer.service.marca;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alcarrer.entity.MarcaEntity;
import com.alcarrer.repository.MarcaRepository;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MarcaServiceTest {

	@Autowired
	private MarcaRepository repository;
 	private PodamFactory factory;

	private MarcaEntity marca;
	
	Integer marcaId = null;
	
	@Before
	public void setUp() {
		factory = new  PodamFactoryImpl();
		marca = factory.manufacturePojo(MarcaEntity.class);
		MarcaEntity id = repository.save(marca);
		marcaId = id.getCodigo();
		
	}

	@Test
	public void incluir() {
		repository.save(marca);
//		 Marca	
	};

	@Test
	public void alterar() {
		MarcaEntity marcaReturn = repository.getOne(marcaId);
	
		assertEquals(marcaReturn.getCodigo(), marcaId);
		assertEquals(marcaReturn.getNome(), marca.getNome());
		assertEquals(marcaReturn.getDescricao(), marca.getDescricao());
		
		marcaReturn.setNome("update");
		
		MarcaEntity savedMarca = repository.save(marcaReturn);
		
		assertEquals(marcaReturn.getCodigo(), savedMarca.getCodigo());
		assertEquals(marcaReturn.getNome(), savedMarca.getNome());
		assertEquals(marcaReturn.getDescricao(), savedMarca.getDescricao());
		
	};

	@Test
	public void excluir() {
		repository.delete(marca);
	};
	
	@Test
	public void consultar() {
		repository.findAll();
//		List<Marca> 
	};

	@Test
	public void consultarByCodigo() {

	};

}
