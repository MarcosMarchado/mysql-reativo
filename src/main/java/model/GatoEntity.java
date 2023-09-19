package model;


import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString
public class GatoEntity  extends PanacheEntityBase {
    @Id
    private Long id;
    private String nome;
    private String raca;

    @Id
    private Long numeroVersao;

}
