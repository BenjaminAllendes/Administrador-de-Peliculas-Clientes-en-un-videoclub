import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

public class VideoclubGUI extends JFrame {
    private Videoclub videoclub;
    private JTextArea displayArea;

    public VideoclubGUI(Videoclub vc) {
        super("Videoclub - Gestión de Arriendos");
        this.videoclub = vc;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                // Guardar datos al salir
                videoclub.saveData();
                //Generar Reporte
                videoclub.generarReporteTXT("reporte_videoclub.txt");
                System.exit(0); // Luego de guardar, salir
            }
        });




        setSize(900, 700);
        setLayout(new BorderLayout());

        // Área de texto para mostrar logs y resultados
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones para el menú principal en el lado izquierdo
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Botones en columna
        add(menuPanel, BorderLayout.WEST);

        // Creación y adición de botones
        JButton registerButton = new JButton("1. Registrarse");
        JButton rentButton = new JButton("2. Realizar Arriendo");
        JButton returnButton = new JButton("4. Devolver Película");
        JButton showRentsButton = new JButton("5. Ver Arriendos Activos");
        JButton showClientsButton = new JButton("6. Ver Socios");
        JButton showMoviesButton = new JButton("7. Ver Catálogo");
        JButton updateStockButton = new JButton("8. Actualizar Stock");
        JButton manageClientsButton = new JButton("9. Gestionar Clientes");
        JButton viewClientProfileButton = new JButton("10. Ver Perfil de Cliente");

        JButton exitButton = new JButton("0. Salir");

        menuPanel.add(registerButton);
        menuPanel.add(rentButton);
        menuPanel.add(returnButton);
        menuPanel.add(showRentsButton);
        menuPanel.add(showClientsButton);
        menuPanel.add(showMoviesButton);
        menuPanel.add(updateStockButton);
        menuPanel.add(exitButton);
        menuPanel.add(manageClientsButton); // NUEVO
        menuPanel.add(viewClientProfileButton);
        menuPanel.add(exitButton);

        // --- MANEJO DE EVENTOS (Lambda Expressions) ---
        registerButton.addActionListener(e -> handleRegistration());
        rentButton.addActionListener(e -> handleRent());
        returnButton.addActionListener(e -> handleReturn());
        showRentsButton.addActionListener(e -> displayRents());
        showClientsButton.addActionListener(e -> displayClients());
        showMoviesButton.addActionListener(e -> displayMovies());
        updateStockButton.addActionListener(e -> handleStockUpdate());
        manageClientsButton.addActionListener(e -> handleManageClient());
        viewClientProfileButton.addActionListener(e -> handleViewClientProfile());
        exitButton.addActionListener(e -> {
            // Guardar datos antes de salir
            videoclub.saveData();

            // Generar reporte
            videoclub.generarReporteTXT("reporte_videoclub.txt");

            // Finalmente salir
            System.exit(0);
        });
        setVisible(true);
    }

    // ======================================
    // MÉTODOS PARA CADA FUNCIONALIDAD
    // ======================================

    private void handleRegistration() {
        String name = JOptionPane.showInputDialog(this, "Ingrese su nombre:");
        if (name != null && !name.isEmpty()) {
            int id = (int) (Math.random() * 9000) + 1000;
            Client newClient = new Client(id, name);
            videoclub.addClient(newClient);
            displayArea.append("✅ Cliente '" + name + "' añadido con ID: " + id + "\n");
        }
    }


    private void handleRent() {
        try {
            int clientID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID del cliente:"));
            int movieID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID de la película:"));
            int days = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese días de arriendo:"));

            // Ya no necesitamos la variable 'boolean'. Si este método falla,
            // lanzará una excepción que será capturada abajo.
            videoclub.rentMovie(clientID, movieID, days);

            // Si el código llega a esta línea, significa que no hubo excepciones y todo salió bien.
            displayArea.append("✅ Arriendo realizado con éxito.\n");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);

        } catch (RecursoNoEncontradoException | PeliculaSinStockException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error de Arriendo", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReturn() {
        try {
            int clientID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID del cliente:"));
            int movieID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID de la película a devolver:"));

            videoclub.returnMovie(clientID, movieID);
            displayArea.append("✅ Película devuelta correctamente.\n");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);

        } catch (RecursoNoEncontradoException e) { // Aquí solo puede ocurrir esta excepción personalizada
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error en Devolución", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayRents() {
        displayArea.append("===== LISTA DE ARRIENDOS ACTIVOS =====\n");
        displayArea.append(videoclub.getRentsInfo()); // Usamos el nuevo metodo
        displayArea.append("====================================\n");
    }

    private void displayClients() {
        displayArea.append("===== LISTA DE CLIENTES REGISTRADOS =====\n");
        displayArea.append(videoclub.getClientsInfo());
        displayArea.append("=========================================\n");
    }

    private void displayMovies() {
        displayArea.append("===== CATÁLOGO DE PELÍCULAS =====\n");
        displayArea.append(videoclub.getMoviesInfo());
        displayArea.append("=================================\n");
    }

    private void handleStockUpdate() {
        try {
            int movieID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID de la película a actualizar:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad a agregar al stock:"));

            Movie movieToUpdate = videoclub.findMovieByID(movieID);
            movieToUpdate.increaseStock(cantidad);
            displayArea.append("✅ Stock de '" + movieToUpdate.getTitle() + "' actualizado. Nuevo stock: " + movieToUpdate.getStock() + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID o cantidad válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleManageClient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del cliente a gestionar:");
            if (idStr == null) return; // El usuario canceló
            int clientID = Integer.parseInt(idStr);
            Client client = videoclub.findClientByID(clientID); // Valida que exista

            Object[] options = {"Modificar Nombre", "Eliminar Cliente", "Cancelar"};
            int choice = JOptionPane.showOptionDialog(this,
                    "¿Qué desea hacer con el cliente '" + client.getName() + "'?",
                    "Gestionar Cliente",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) { // Modificar Nombre
                String newName = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre:", client.getName());
                if (newName != null && !newName.trim().isEmpty()) {
                    videoclub.modifyClientName(clientID, newName);
                    displayArea.append("✅ Nombre del cliente " + clientID + " actualizado a '" + newName + "'.\n");
                }
            } else if (choice == 1) { // Eliminar Cliente
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de que desea eliminar a '" + client.getName() + "'? Esta acción no se puede deshacer.",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    videoclub.removeClient(clientID);
                    displayArea.append("✅ Cliente '" + client.getName() + "' eliminado.\n");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RecursoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleViewClientProfile() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del cliente para ver su perfil:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int clientID = Integer.parseInt(idStr);

            Client client = videoclub.findClientByID(clientID);

            StringBuilder profile = new StringBuilder();
            profile.append("===== PERFIL DEL CLIENTE =====\n");
            profile.append("ID: ").append(client.getId()).append("\n");
            profile.append("Nombre: ").append(client.getName()).append("\n\n");

            profile.append("--- Arriendos Activos ---\n");
            profile.append(client.getActiveRentsInfo()); //

            profile.append("\n--- Historial de Arriendos ---\n");
            profile.append(client.getPastRentsInfo()); //

            profile.append("==========================\n");

            // El resto del código para mostrar el JOptionPane se mantiene igual
            JTextArea textArea = new JTextArea(profile.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Perfil de " + client.getName(), JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RecursoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}