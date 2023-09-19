package controller;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.GatoEntity;
import model.GatoRepository;

@Path("/gato")
public class GatoController {

    @Inject
    GatoRepository repository;

    private final static Long NUMERO_SEQUENCIAL_INICIAL = 0L;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<GatoEntity> fazAlgo(){

        GatoEntity gatoEntity = GatoEntity.builder()
                .raca("SRD")
                .id(3L)
                .nome("Bento")
                .build();

        Uni<Long> idSequencial = repository.buscaUltimaVersao(gatoEntity.getId()).replaceIfNullWith(NUMERO_SEQUENCIAL_INICIAL);

        return repository.salva(gatoEntity, idSequencial);
    }



}
