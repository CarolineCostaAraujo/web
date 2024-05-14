package br.com.fabios.web.repository;

import br.com.fabios.web.models.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministradoresRepository extends CrudRepository<Administrador, Integer> {
    @Query(value = "select CASE WHEN count(1) > 0 then 'true' else 'false' END from administradores where id = :id", nativeQuery = true)
    public boolean exist(int id);
    @Query(value = "select * from administradores where email = :email and senha = :senha", nativeQuery = true)
    public Administrador login(String email, String senha);
}
