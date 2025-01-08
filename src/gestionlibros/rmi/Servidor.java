package gestionlibros.rmi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Servidor extends UnicastRemoteObject implements Biblioteca {
    private List<Libro> libros;

    protected Servidor() throws RemoteException {
        libros = new ArrayList<>();
    }

    @Override
    public void agregarLibro(Libro libro) throws RemoteException {
        libros.add(libro);
    }

    @Override
    public Libro buscarLibro(String titulo) throws RemoteException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null; 
    }

    @Override
    public List<Libro> listarLibros() throws RemoteException {
        return libros;
    }

    public static void main(String[] args) {
        try {
            Biblioteca biblioteca = new Servidor();
            Registry r = LocateRegistry.createRegistry(6000);
            r.rebind("Biblioteca", biblioteca);
            System.out.println("Servidor de biblioteca ejecutándose!");
        } catch (RemoteException ex) {
            System.err.println(ex);
        }
    }
}