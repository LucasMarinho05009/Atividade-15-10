import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private int ramTotal;
    private boolean statusLigado;
    private Set<Container> containers;

    public Servidor(String ip, double precoPorHora, int ramTotal, boolean statusLigado) {
        if (precoPorHora < 0 || ramTotal < 0) throw new IllegalArgumentException("Preço e RAM não podem ser negativos.");
        this.ip = ip;
        this.precoPorHora = precoPorHora;
        this.ramTotal = ramTotal;
        this.statusLigado = statusLigado;
        this.containers = new HashSet<>();
    }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public double getPrecoPorHora() { return precoPorHora; }
    public void setPrecoPorHora(double precoPorHora) { this.precoPorHora = precoPorHora; }
    public int getRamTotal() { return ramTotal; }
    public void setRamTotal(int ramTotal) { this.ramTotal = ramTotal; }
    public boolean isStatusLigado() { return statusLigado; }
    public void setStatusLigado(boolean statusLigado) { this.statusLigado = statusLigado; }
    public Set<Container> getContainers() { return new HashSet<>(containers); }
    public int getRamEmUso() { return containers.stream().mapToInt(Container::getRamNecessaria).sum(); }
    public int getRamDisponivel() { return ramTotal - getRamEmUso(); }

    public void alocarContainer(Container container) throws ServidorOfflineException, CapacidadeExcedidaException {
        if (!statusLigado) throw new ServidorOfflineException("O servidor " + ip + " está desligado.");
        if (container == null) throw new IllegalArgumentException("O container não pode ser nulo.");
        if (getRamDisponivel() < container.getRamNecessaria()) {
            throw new CapacidadeExcedidaException("RAM insuficiente no servidor " + ip + ". Disponível: " + getRamDisponivel() + " GB.");
        }
        containers.add(container);
    }

    public boolean removerContainer(Container container) { return containers.remove(container); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Servidor)) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    @Override
    public int hashCode() { return Objects.hash(ip); }

    @Override
    public String toString() {
        return "Servidor " + ip + " | ligado=" + statusLigado + " | RAM=" + getRamEmUso() + "/" + ramTotal + " GB";
    }
}
