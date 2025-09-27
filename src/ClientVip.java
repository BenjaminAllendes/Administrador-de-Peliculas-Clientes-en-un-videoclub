class ClientVIP extends Client {

    public ClientVIP(int id, String name) {
        super(id, name);
    }

    // Sobrescribimos el método para mostrar arriendos activos
    @Override
    public String getActiveRentsInfo() {
        String original = super.getActiveRentsInfo();
        if (!original.equals("No tiene arriendos activos.\n")) {
            original = "🌟 CLIENTE VIP 🌟\n" + original;
            original += "🔁 Este cliente puede renovar 1 día adicional gratis.\n";
        }
        return original;
    }

    // Sobrescribimos el método para mostrar historial de arriendos
    @Override
    public String getPastRentsInfo() {
        String original = super.getPastRentsInfo();
        if (!original.equals("No tiene historial de arriendos.\n")) {
            original = "🌟 HISTORIAL VIP DE " + getName() + " 🌟\n" + original;
        }
        return original;
    }

    @Override
    public boolean isVIP() {
        return true;
    }

}
