package br.com.prog3.trabalhofinal.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prog3.trabalhofinal.domain.Marca;
import br.com.prog3.trabalhofinal.service.MarcaService;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaResource {
	@Autowired
	private MarcaService marcaService;

	@GetMapping
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	public ResponseEntity<List<Marca>> findAll() {
		List<Marca> marcas = marcaService.findAll();
		if (marcas == null || marcas.isEmpty()) {
			return new ResponseEntity<List<Marca>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Marca>>(marcas, HttpStatus.OK);
	}

	@PostMapping
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	public Marca create(@RequestBody Marca marca) {
		return marcaService.save(marca);
	}

	@GetMapping(path = { "/{id}" })
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return marcaService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	public ResponseEntity<Marca> update(@PathVariable("id") Long id, @RequestBody Marca marca) {
		return marcaService.findById(id).map(record -> {
			record.setSigla(marca.getSigla());
			record.setDescricao(marca.getDescricao());
			Marca updated = marcaService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return marcaService.findById(id).map(record -> {
			marcaService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
