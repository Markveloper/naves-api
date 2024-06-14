package com.prueba.naves_api;

import com.prueba.naves_api.controller.NaveController;
import com.prueba.naves_api.model.Nave;
import com.prueba.naves_api.service.NaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class NaveApiApplicationTests {

	@InjectMocks
	private NaveController naveController;

	@Mock
	private NaveService naveService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllNaves() {
		List<Nave> naves = new ArrayList<>();
		naves.add(new Nave(1L, "Nave 1", "Serie"));
		naves.add(new Nave(2L, "Nave 2", "Serie"));
		Page<Nave> page = new PageImpl<>(naves);

		when(naveService.getAllNaves(any(Pageable.class))).thenReturn(page);

		Page<Nave> result = naveController.getAllNaves(Pageable.unpaged());

		assertEquals(2, result.getTotalElements());
	}

	@Test
	public void testGetNaveById() {
		Long id = 1L;
		Nave nave = new Nave(id, "Nave 1", "Serie");

		when(naveService.getNaveById(id)).thenReturn(nave);

		ResponseEntity<Nave> response = naveController.getNaveById(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(nave, response.getBody());
	}

	@Test
	public void testGetNaveByIdNotFound() {
		Long id = 1L;

		when(naveService.getNaveById(id)).thenReturn(null);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			naveController.getNaveById(id);
		});

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
		assertEquals(id, Long.parseLong(exception.getReason()));
	}

}
