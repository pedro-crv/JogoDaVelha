import java.io.ObjectOutputStream;

/**
 * Compartilha dados dos Streams de envio de dados aos clientes entre as Threads
 * Servindo. Cada dois Clientes jogando entre si, usam uma instância desta
 * classe.
 */
class StreamsDeSaida {
    ObjectOutputStream os[] = new ObjectOutputStream[2];
    int cont = 0;
}
