import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente não pode ser nulo.");
        bancoDeDados.putIfAbsent(cliente, new HashSet<>());
    }

    public void adicionarServidor(Cliente cliente, Servidor servidor) {
        if (cliente == null || servidor == null) throw new IllegalArgumentException("Cliente e servidor são obrigatórios.");
        cadastrarCliente(cliente);
        bancoDeDados.get(cliente).add(servidor);
    }

    public void alocarContainer(Cliente cliente, String ipServidor, Container container)
            throws ServidorOfflineException, CapacidadeExcedidaException {
        Set<Servidor> servidores = bancoDeDados.get(cliente);
        if (servidores == null) throw new IllegalArgumentException("Cliente não cadastrado.");
        Servidor servidor = servidores.stream()
                .filter(s -> s.getIp().equals(ipServidor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Servidor não encontrado para este cliente."));
        servidor.alocarContainer(container);
    }

    public double calcularFatura(Cliente cliente, int horas) {
        if (horas < 0) throw new IllegalArgumentException("As horas não podem ser negativas.");
        return bancoDeDados.getOrDefault(cliente, Collections.emptySet()).stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum() * horas;
    }

    public List<Servidor> listarServidoresLigados() {
        return bancoDeDados.values().stream()
                .flatMap(Set::stream)
                .filter(Servidor::isStatusLigado)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Container> listarContainers() {
        return bancoDeDados.values().stream()
                .flatMap(Set::stream)
                .flatMap(s -> s.getContainers().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public int calcularRamTotalEmUso(Cliente cliente) {
        return bancoDeDados.getOrDefault(cliente, Collections.emptySet()).stream()
                .mapToInt(Servidor::getRamEmUso).sum();
    }

    public Map<Cliente, Set<Servidor>> getBancoDeDados() {
        Map<Cliente, Set<Servidor>> copia = new HashMap<>();
        bancoDeDados.forEach((cliente, servidores) -> copia.put(cliente, new HashSet<>(servidores)));
        return copia;
    }
}
