package br.com.fabios.web.repository;

import br.com.fabios.web.models.Administrador;
import br.com.fabios.web.models.JurosCompostos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JurosCompostosRepository extends CrudRepository<JurosCompostos, Integer> {
    @Query(value = "select CASE WHEN count(1) > 0 then 'true' else 'false' END from jurosCompostos where id = :id", nativeQuery = true)
    public boolean exist(int id);

}
