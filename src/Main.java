import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // --- INICIALIZACIÓN ---
        Videoclub vc = new Videoclub();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;

        // --- BASE DE DATOS PRE-CARGADA ---
        vc.addMovie(new Movie(1, "Matrix", "Acción", 2));
        vc.addMovie(new Movie(2, "Titanic", "Romance", 3));
        vc.addMovie(new Movie(3, "El Señor de los Anillos", "Fantasía", 5));
        vc.addMovie(new Movie(4, "Toy Story", "Animación", 4));
        vc.addMovie(new Movie(5, "Los Increíbles", "Animación", 0)); // Película sin stock para pruebas
        System.out.println("✅ 5 películas precargadas en el sistema.");

        // --- BUCLE DEL MENÚ PRINCIPAL ---
        while (running) {
            System.out.println("\n===== MENÚ VIDEOCLUB =====");
            System.out.println("1. Registrarse como cliente");
            System.out.println("2. Realizar Arriendo");
            System.out.println("3. Ver recomendaciones");
            System.out.println("4. Devolver Película");
            System.out.println("5. Ver Arriendos Activos");
            System.out.println("6. Ver Lista de Socios");
            System.out.println("7. Ver Catálogo de Películas");
            System.out.println("8. Actualizar Stock (SOLO EMPLEADOS)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            // ÚNICO TRY-CATCH que envuelve toda la lógica del menú
            try {
                int option = Integer.parseInt(br.readLine());

                switch (option) {
                    case 1: // REGISTRO DE CLIENTE
                        int id = (int)(Math.random() * 9000) + 1000;
                        System.out.println("ID asignado automáticamente: " + id);
                        System.out.print("Ingrese su nombre: ");
                        String name = br.readLine();
                        vc.addClient(new Client(id, name));
                        System.out.println("Cliente '" + name + "' añadido correctamente con ID " + id);
                        break;

                    case 2: // REALIZAR ARRIENDO
                        // Se eliminó el try-catch de aquí para que el exterior lo maneje
                        System.out.println("\n--- Seleccione el Cliente ---");
                        vc.showClients();
                        System.out.print("Ingrese el ID del cliente: ");
                        int clientID = Integer.parseInt(br.readLine());
                        Client clienteSeleccionado = vc.findClientByID(clientID);
                        System.out.println("-> Cliente seleccionado: " + clienteSeleccionado.getName());

                        System.out.println("\n--- Catálogo de Películas Disponibles ---");
                        vc.showMovies();
                        System.out.print("\nIngrese el ID de la película que desea arrendar: ");
                        int movieID = Integer.parseInt(br.readLine());

                        System.out.print("Ingrese días de arriendo: ");
                        int days = Integer.parseInt(br.readLine());

                        vc.rentMovie(clientID, movieID, days);
                        System.out.println("✅ Arriendo realizado con éxito para " + clienteSeleccionado.getName() + ".");
                        break;

                    case 3: // VER RECOMENDACIONES (Funcionalidad pendiente)
                        System.out.println("\n--- Seleccione el Cliente ---");
                        vc.showClients();
                        System.out.print("Ingrese el ID del cliente para ver sus recomendaciones: ");
                        int recClientID = Integer.parseInt(br.readLine());
                        Client c3 = vc.findClientByID(recClientID);
                        System.out.println("===== RECOMENDACIONES PARA " + c3.getName() + " =====");
                        System.out.println("(Funcionalidad de recomendaciones aún no implementada)");
                        break;

                    case 4: // DEVOLVER PELÍCULA
                        System.out.println("\n--- Seleccione el Cliente ---");
                        vc.showClients();
                        System.out.print("Ingrese el ID del cliente que realiza la devolución: ");
                        int clientReturnID = Integer.parseInt(br.readLine());
                        Client c2 = vc.findClientByID(clientReturnID);

                        System.out.println("\nPelículas actualmente arrendadas por " + c2.getName() + ":");
                        c2.mostrarArriendosActivos();

                        System.out.print("Ingrese ID de la película a devolver: ");
                        int movieReturnID = Integer.parseInt(br.readLine());

                        vc.returnMovie(clientReturnID, movieReturnID);
                        System.out.println("✅ Película devuelta correctamente.");
                        break;

                    case 5: // MOSTRAR LISTA DE ARRIENDOS ACTIVOS
                        System.out.println("\n===== LISTA DE ARRIENDOS ACTIVOS =====");
                        vc.showRents();
                        break;

                    case 6: // MOSTRAR CLIENTES
                        System.out.println("\n===== LISTA DE CLIENTES REGISTRADOS =====");
                        vc.showClients();
                        break;

                    case 7: // MOSTRAR PELÍCULAS
                        System.out.println("\n===== CATÁLOGO DE PELÍCULAS =====");
                        vc.showMovies();
                        break;

                    case 8: // ACTUALIZAR STOCK
                        System.out.println("\n--- Catálogo de Películas ---");
                        vc.showMovies();
                        System.out.print("Ingrese el ID de la película para actualizar su stock: ");
                        int movieIDSt = Integer.parseInt(br.readLine());
                        Movie movieToUpdate = vc.findMovieByID(movieIDSt);

                        System.out.print("Ingrese la cantidad a agregar al stock: ");
                        int cantidad = Integer.parseInt(br.readLine());
                        movieToUpdate.increaseStock(cantidad);
                        System.out.println("✅ Stock de '" + movieToUpdate.getTitle() + "' actualizado. Nuevo stock: " + movieToUpdate.getStock());
                        break;

                    case 0: // SALIR
                        running = false;
                        System.out.println("Saliendo del Videoclub... ¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, elija un número del menú.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido. Intente de nuevo.");
            } catch (RecursoNoEncontradoException | PeliculaSinStockException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }
}