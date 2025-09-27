import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Videoclub vc = new Videoclub();

            /*/ --- BASE DE DATOS PRE-CARGADA ---
            vc.addMovie(new Movie(1, "Matrix", "Acción", 2));
            vc.addMovie(new Movie(2, "Titanic", "Romance", 3));
            vc.addMovie(new Movie(3, "El Señor de los Anillos", "Fantasía", 5));
            vc.addMovie(new Movie(4, "Toy Story", "Animación", 4));
            vc.addMovie(new Movie(5, "Los Increíbles", "Animación", 0));
*/
            vc.loadData();
            //System.out.println("5 películas precargadas en el sistema.");

            // Se inicializa y muestra la ventana de la aplicación
            new VideoclubGUI(vc);
            vc.saveData();
            vc.generarReporteTXT("reporte_videoclub.txt");
        });

    }
}

