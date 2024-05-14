package br.com.fabios.web.repository;

import br.com.fabios.web.models.Administrador;
import org.springframework.data.repository.CrudRepository;

public interface AdministradoresRepository extends CrudRepository<Administrador, Integer> {
}
