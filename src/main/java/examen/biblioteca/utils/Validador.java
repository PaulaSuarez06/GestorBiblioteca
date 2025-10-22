package examen.biblioteca.utils;

public class Validador {

    public static boolean esTextoValido(String texto, int min, int max) {
        return texto != null && !texto.trim().isEmpty()
                && texto.length() >= min
                && texto.length() <= max;
    }


    public static boolean esNumero(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        try {
            Long.parseLong(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esFecha(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        try {
            java.time.LocalDate.parse(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
