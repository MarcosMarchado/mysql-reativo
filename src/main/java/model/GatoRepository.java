package model;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
//https://quarkus.io/guides/hibernate-reactive-panache#transactions
//https://quarkus.io/guides/getting-started-reactive
//https://medium.com/geekculture/creating-a-crud-app-with-quarkus-reactive-hibernate-panache-and-graphql-using-a-postgresql-216ecd75ee52
@ApplicationScoped
public class GatoRepository implements PanacheRepository<Object> {

    public Uni<Long> buscaUltimaVersao(Long id){
        return find("SELECT max(a.numeroVersao) FROM GatoEntity a WHERE a.id = ?1", id)
                .singleResult();
    }

    public Uni<GatoEntity> salva(GatoEntity gatoEntity, Uni<Long> idSequencial) {
        return Panache.withTransaction(() -> idSequencial
                .onItem().ifNotNull()
                .transform(id -> setaIdSequencial(gatoEntity, id))
                .onItem().call(gatoEntityUni -> gatoEntity.persist()));
    }


    private static GatoEntity setaIdSequencial(GatoEntity gatoEntity, Long id) {
        long idSeqFinal = criaIdSequencial(id);
        gatoEntity.setNumeroVersao(idSeqFinal);
        return gatoEntity;
    }
    private static long criaIdSequencial(Long id) {
        return id == 0 ? 1 : id + 1;
    }

}
