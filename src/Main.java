import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Videoclub vc = new Videoclub();


            vc.loadData();
            //System.out.println("5 películas precargadas en el sistema.");

            // Se inicializa y muestra la ventana de la aplicación
            new VideoclubGUI(vc);
            vc.saveData();
            vc.generarReporteTXT("reporte_videoclub.txt");
        });

    }
}

