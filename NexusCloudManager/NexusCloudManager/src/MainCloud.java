public class MainCloud {
    public static void main(String[] args) {
        CloudManager cloud = new CloudManager();
        Cliente cliente = new Cliente("12345678900", "Ana Souza");
        Servidor servidor1 = new Servidor("192.168.0.10", 2.50, 16, true);
        Servidor servidor2 = new Servidor("192.168.0.11", 1.75, 8, false);

        cloud.cadastrarCliente(cliente);
        cloud.adicionarServidor(cliente, servidor1);
        cloud.adicionarServidor(cliente, servidor2);

        try {
            cloud.alocarContainer(cliente, "192.168.0.10", new Container("C001", "Site institucional", 4));
            cloud.alocarContainer(cliente, "192.168.0.10", new Container("C002", "Banco de dados", 6));
            cloud.alocarContainer(cliente, "192.168.0.11", new Container("C003", "Aplicativo", 2));
        } catch (ServidorOfflineException | CapacidadeExcedidaException e) {
            System.out.println("Não foi possível alocar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("=== NexusCloud Manager ===");
        System.out.println("Cliente: " + cliente);
        System.out.println("Servidores ligados:");
        cloud.listarServidoresLigados().forEach(System.out::println);
        System.out.println("Containers alocados:");
        cloud.listarContainers().forEach(System.out::println);
        System.out.printf("RAM utilizada pelo cliente: %d GB%n", cloud.calcularRamTotalEmUso(cliente));
        System.out.printf("Fatura estimada para 24 horas: R$ %.2f%n", cloud.calcularFatura(cliente, 24));
    }
}
