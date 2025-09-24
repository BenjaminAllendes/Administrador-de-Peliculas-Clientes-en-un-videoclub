import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Videoclub vc = new Videoclub(); // Instancia del videoclub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // ==============================
        // BASE DE DATOS PRE-CARGADA
        // ==============================
        vc.addMovie(new Movie(1, "Matrix", "Acción", 10));
        vc.addMovie(new Movie(2, "Titanic", "Romance", 15));
        vc.addMovie(new Movie(3, "El señor de los anillos", "Fantasía", 11));
        vc.addMovie(new Movie(4, "Toy Story", "Animación", 10));
        vc.addMovie(new Movie(5, "Los Increibles", "Animación", 17));

        System.out.println("5 películas precargadas en el sistema.");

        boolean running = true;

        // ==============================
        // BUCLE DEL MENÚ PRINCIPAL
        // ==============================
        while (running) {
            System.out.println("\n===== MENÚ VIDEOCLUB =====");
            System.out.println("1. Registrarse");
            System.out.println("2. Realizar Arriendo");
            System.out.println("3. Ver recomendaciones");
            System.out.println("4. Terminar Arriendo");
            System.out.println("5. Ver lista de Arriendos");
            System.out.println("6. Ver Socios");
            System.out.println("7. Ver Películas");
            System.out.println("8. Actualizar Stock (SOLO EMPLEADOS)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            int option = Integer.parseInt(br.readLine());

            switch (option) {
                case 1: // REGISTRO DE CLIENTE
                    int id = (int)(Math.random()*9000) + 1000;
                    System.out.println("ID asignado automáticamente: " + id);

                    // SUBMENÚ PARA ELEGIR EL TIPO DE NOMBRE
                    System.out.println("¿Cómo desea registrar su nombre?");
                    System.out.println("1. Solo nombre");
                    System.out.println("2. Nombre y apellido");
                    System.out.println("Escoja una opción: ");

                    int nameOption = Integer.parseInt(br.readLine());
                    String name ;
                    switch (nameOption) {
                        case 1:
                            System.out.println("Ingrese su nombre: ");
                            name = br.readLine();
                            vc.addClient(new Client(id, name));
                            System.out.println("Cliente añadido correctamente con ID " + id);
                            break ;

                        case 2:
                            System.out.println("Ingrese su nombre: ");
                            String firstName = br.readLine();
                            System.out.println("Ingrese su apellido: ");
                            String lastName = br.readLine();

                            // Se crea una instancia Client y se usa la sobrecarga de métodos
                            Client newClient = new Client(id, "") ;
                            newClient.setName(firstName, lastName);
                            vc.addClient(newClient);
                            System.out.println("Cliente añadido correctamente con ID " + id);
                            break;

                        default:
                            System.out.println("Opción de nombre inválido. Registro cancelado.");
                            break ;
                    }

                    break;

                case 2: // REALIZAR ARRIENDO
                    System.out.println("\n--- Seleccione el Cliente ---");
                    vc.showClients(); // <-- MEJORA: Muestra la lista de clientes
                    System.out.print("Ingrese el ID del cliente que realiza el arriendo: ");
                    int clientID = Integer.parseInt(br.readLine());
                    Client c1 = vc.findClientByID(clientID);
                    if (c1 == null) {
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                        break;
                    }
                    System.out.println("\n--- Catálogo de Películas Disponibles ---");
                    vc.showMovies(); // <-- MEJORA: Muestra la lista de películas
                    System.out.print("\nIngrese el ID de la película que desea arrendar: ");
                    int movieID = Integer.parseInt(br.readLine());
                    System.out.println("Ingrese días de arriendo: ");
                    int days = Integer.parseInt(br.readLine());

                    if (vc.rentMovie(clientID, movieID, days)) {
                        System.out.println("Arriendo realizado con éxito.");
                    } else {
                        System.out.println("NO se pudo realizar el arriendo.");
                    }
                    break;

                case 3: // VER RECOMENDACIONES

                    System.out.println("\n--- Seleccione el Cliente ---");
                    vc.showClients();
                    System.out.print("Ingrese el ID del cliente para ver sus recomendaciones: ");
                    int recClientID = Integer.parseInt(br.readLine());
                    Client c3 = vc.findClientByID(recClientID);
                    if (c3 == null){
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                        break;
                    }

                    System.out.println("===== RECOMENDACIONES PARA " + c3.getName() + " =====") ;
                    break;

                case 4: // DEVOLVER PELÍCULA
                    System.out.println("\n--- Seleccione el Cliente ---");
                    vc.showClients();
                    System.out.print("Ingrese el ID del cliente que realiza la devolucion: ");
                    int clientReturnID = Integer.parseInt(br.readLine());
                    Client c2 = vc.findClientByID(clientReturnID);
                    if (c2 == null) {
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                        break;
                    }
                    System.out.println("\nPelículas actualmente arrendadas por " + c2.getName() + ":");
                    c2.mostrarArriendosActivos(); // 💡
                    System.out.println("Ingrese ID de la película: ");
                    int movieReturnID = Integer.parseInt(br.readLine());

                    if (vc.returnMovie(clientReturnID, movieReturnID)) {
                        System.out.println("Arriendo finalizado.");
                    } else {
                        System.out.println("No se encontró este arriendo.");
                    }
                    break;

                case 5: // MOSTRAR LISTA DE ARRIENDOS ACTIVOS
                    System.out.println("===== LISTA ARRIENDOS =====");
                    vc.showRents();
                    break;

                case 6: // MOSTRAR CLIENTES
                    System.out.println("===== CLIENTES =====");
                    vc.showClients();
                    break;

                case 7: // MOSTRAR PELÍCULAS
                    System.out.println("===== PELÍCULAS =====");
                    vc.showMovies();
                    break;

                case 8: // ACT STOCK
                    System.out.println("\n--- Catálogo de Películas ---");
                    vc.showMovies(); // <-- MEJORA: Muestra la lista de películas
                    System.out.print("Ingrese el ID de la película que desea actualizar su stock: ");
                    int movieIDSt = Integer.parseInt(br.readLine());

                    Movie movieToUpdate = vc.findMovieByID(movieIDSt);

                    if (movieToUpdate == null) {
                        System.out.println("No se encontró una película con el ID " + movieIDSt);
                        break ;
                    }

                    System.out.println("¿Como desea actualizar el stock?");
                    System.out.println("1. Aumentar en 1 unidad.");
                    System.out.println("2. Aumentar en una cantidad especifica.");
                    System.out.println("Eliga una opción: ");
                    int stockOption = Integer.parseInt(br.readLine()) ;

                    switch (stockOption) {
                        case 1:
                            movieToUpdate.increaseStock();
                            System.out.println("Stock de "+ movieToUpdate.getTitle()+" actualizado en 1 unidad. Nuevo stock: " + movieToUpdate.getStock());
                            break ;

                        case 2:
                            System.out.println("Ingrese la cantidad a agregar: ");
                            int cantidad = Integer.parseInt(br.readLine());
                            movieToUpdate.increaseStock(cantidad);
                            System.out.println("Stock de "+ movieToUpdate.getTitle()+" actualizado en: " + cantidad + " unidades. Nuevo stock: " + movieToUpdate.getStock());
                            break;

                        default:
                            System.out.println("Opción inválida.");
                            break ;
                    }
                    break;

                case 0: // SALIR
                    running = false;
                    System.out.println("Saliendo del Videoclub...");
                    break;

                default:
                    System.out.println("OPCIÓN INVÁLIDA.");
                    break;
            }
        }
    }
}
