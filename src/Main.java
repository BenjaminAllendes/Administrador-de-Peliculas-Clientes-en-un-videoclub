import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Videoclub vc = new Videoclub();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // MINI BASE DE DATOS DE PELÍCULAS - PRE CSV
        vc.addMovie(new Movie(001, "Matrix", "Acción", 1));
        vc.addMovie(new Movie(002, "Titanic", "Romance", 1));
        vc.addMovie(new Movie(003, "El señor de los anillos", "Fantasía", 1));
        vc.addMovie(new Movie(004, "Toy Story", "Animación", 1));
        vc.addMovie(new Movie(005, "Los Increibles", "Animación", 1));


        System.out.println("5 películas precargadas en el sistema.");

        boolean running = true;

        while (running) {
            System.out.println("\n===== MENÚ VIDEOCLUB =====");
            System.out.println("1. Registrarse");
            System.out.println("2. Realizar Arriendo");
            System.out.println("3. Ver recomendaciones");
            System.out.println("4. Terminar Arriendo");
            System.out.println("5. Ver lista de Arriendos");
            System.out.println("6. Ver Socios");
            System.out.println("7. Ver Películas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            int option = Integer.parseInt(br.readLine()) ;

            switch (option) {
                case 1 :
                    int id = (int)(Math.random()*9000) + 1000 ;

                    System.out.println("ID asignado automáticamente: " + id);
                    System.out.println("Ingrese su nombre: ");
                    String name = br.readLine();

                    vc.addClient(new Client(id, name));
                    System.out.println("Cliente añadido correctamente con ID " + id);
                    break ;

                case 2 :
                    System.out.println("Ingrese su ID de Cliente: ");
                    int clientID = Integer.parseInt(br.readLine()) ;
                    Client c1 = vc.findClientByID(clientID);
                    if (c1 ==  null) {
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                        break ;
                    }

                    System.out.println("Ingrese ID de la película: ");
                    int movieID = Integer.parseInt(br.readLine()) ;
                    System.out.println("Ingrese días de arriendo: ");
                    int days = Integer.parseInt(br.readLine()) ;

                    if (vc.rentMovie(clientID, movieID, days)) {
                        System.out.println("Arriendo realizado con éxito.");
                    } else {
                        System.out.println("NO se pudo realizar el arriendo.");
                    }
                    break ;

                case 3 :
                    System.out.println("Ingrese su ID de Cliente: ");
                    int recClientID = Integer.parseInt(br.readLine()) ;

                    Client c3 = vc.findClientByID(recClientID) ;
                    if (c3 == null){
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                    }

                    System.out.println("===== RECOMENDACIONES PARA " + c3.getName() + " =====");
                    if (c3.getRecommended().isEmpty()) {
                        System.out.println("No hay recomendaciones disponibles.");
                    } else {
                        for (String rec : c3.getRecommended()) {
                            System.out.println("- " + rec);
                        }
                    }
                    break ;

                case 4 :
                    System.out.println("Ingrese su ID de Cliente: ");
                    int clientReturnID = Integer.parseInt(br.readLine()) ;

                    Client c2 = vc.findClientByID(clientReturnID);
                    if (c2 == null) {
                        System.out.println("NO EXISTE INFORMACIÓN DE ESTE USUARIO.");
                        break ;
                    }

                    System.out.println("Ingrese ID de la película: ");
                    int movieReturnID = Integer.parseInt(br.readLine()) ;

                    if (vc.returnMovie(clientReturnID, movieReturnID)) {
                        System.out.println("Arriendo finalizado.");
                    } else {
                        System.out.println("No se encontó este arriendo.");
                    }
                    break;

                case 5 :
                    System.out.println("===== LISTA ARRIENDOS =====");
                    vc.showRents();
                    break ;

                case 6 :
                    System.out.println("===== CLIENTES =====");
                    vc.showClients();
                    break;

                case 7 :
                    System.out.println("===== PELICULAS =====");
                    vc.showMovies();
                    break ;

                case 0 :
                    running = false ;
                    System.out.println("Saliendo del Videoclub...");
                    break ;

                default:
                    System.out.println("OPCIÓN INVÁLIDA.");
                    break;

            }
        }
    }
}