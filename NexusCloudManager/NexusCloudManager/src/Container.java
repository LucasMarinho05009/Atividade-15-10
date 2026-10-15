import java.util.Objects;

public class Container {
    private String id;
    private String nomeApp;
    private int ramNecessaria;

    public Container(String id, String nomeApp, int ramNecessaria) {
        if (ramNecessaria <= 0) throw new IllegalArgumentException("A RAM necessária deve ser maior que zero.");
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNomeApp() { return nomeApp; }
    public void setNomeApp(String nomeApp) { this.nomeApp = nomeApp; }
    public int getRamNecessaria() { return ramNecessaria; }
    public void setRamNecessaria(int ramNecessaria) {
        if (ramNecessaria <= 0) throw new IllegalArgumentException("A RAM necessária deve ser maior que zero.");
        this.ramNecessaria = ramNecessaria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Container)) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return nomeApp + " [ID: " + id + ", RAM: " + ramNecessaria + " GB]"; }
}
