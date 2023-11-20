import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {

        try {
            int puerto = 5000; // Número de puerto

            // Crear un socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Dirección IP del servidor
            InetAddress direccionIP_servidor = InetAddress.getByName("172.31.118.82");

            // Leer el mensaje desde la terminal
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el mensaje: ");
            String mensaje = scanner.nextLine();

            // Arreglo de bytes para enviar los datos
            byte[] bufferSalida = mensaje.getBytes();

            // Crear paquete para enviar datos
            DatagramPacket paquete_enviar = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP_servidor, puerto);

            // Enviar paquete
            socket.send(paquete_enviar);

            // Arreglo de bytes para recibir los datos
            byte[] bufferEntrada = new byte[1024];

            // Crear paquete para recibir datos
            DatagramPacket paquete_recibir = new DatagramPacket(bufferEntrada, bufferEntrada.length);

            // Recibir paquete
            socket.receive(paquete_recibir);

            // Extraer la información del paquete recibido
            String mensajeRecibido = new String(paquete_recibir.getData(), 0, paquete_recibir.getLength());
            System.out.println("Mensaje recibido: " + mensajeRecibido);

            // Cerrar el socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
