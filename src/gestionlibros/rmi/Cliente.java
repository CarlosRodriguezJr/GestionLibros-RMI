package gestionlibros.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        try {
            Biblioteca biblioteca = (Biblioteca) Naming.lookup("rmi://192.168.1.83:6000/Biblioteca");

            while (true) {
                String opt = JOptionPane.showInputDialog(
                        "Biblioteca\n" +
                        "Agregar Libro....... (1)\n" +
                        "Buscar Libro........ (2)\n" +
                        "Listar Libros....... (3)\n\n" +
                        "Cancelar para salir"
                );

                if (opt == null) break;
                switch (opt) {
                    case "1": {
                        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
                        String autor = JOptionPane.showInputDialog("Ingrese el autor del libro:");
                        int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicación:"));
                        biblioteca.agregarLibro(new Libro(titulo, autor, anio));
                        JOptionPane.showMessageDialog(null, "Libro agregado!");
                        break;
                    }
                    case "2": {
                        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro a buscar:");
                        Libro libro = biblioteca.buscarLibro(titulo);
                        if (libro != null) {
                            JOptionPane.showMessageDialog(null, libro.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Libro no encontrado!");
                        }
                        break;
                    }
                    case "3": {
                        StringBuilder sb = new StringBuilder();
                        for (Libro libro : biblioteca.listarLibros()) {
                            sb.append(libro.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                        break;
                    }
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            JOptionPane.showMessageDialog(null, "Servidor no conectado:\n" + ex);
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}