package gestionlibros.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Biblioteca extends Remote {
    void agregarLibro(Libro libro) throws RemoteException;
    Libro buscarLibro(String titulo) throws RemoteException;
    List<Libro> listarLibros() throws RemoteException;
}