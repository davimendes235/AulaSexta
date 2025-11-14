package br.senac.lanchonete.repository;
import br.senac.lanchonete.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository  extends CrudRepository<Categoria, Long>{
}
