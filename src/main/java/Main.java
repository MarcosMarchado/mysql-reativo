import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {
    /*Aplicação reativa para consultar o último id de versão de um Gato e
    persistir com o valor incrementado caso seja uma versão mais nova*/
    public static void main(String... args) {
        Quarkus.run(args);
    }

}