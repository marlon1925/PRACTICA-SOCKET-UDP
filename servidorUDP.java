import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class servidorUDP {
    public static void main(String[] args) {

        try {
            int puerto = 5000; // Número de puerto

            // Crear un socket UDP
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor esperando conexiones");

            // Dirección IP del cliente
            InetAddress direccionIP_cliente = InetAddress.getByName("172.31.118.83"); // Ajusta la dirección IP del cliente

            // Bucle principal
            while (true) {
                // Leer mensaje desde la terminal del servidor
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el mensaje para el cliente: ");
                String mensajeServidor = scanner.nextLine();

                // Enviar mensaje al cliente
                byte[] bufferSalida = mensajeServidor.getBytes();
                DatagramPacket paquete_enviar = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP_cliente, puerto);
                socket.send(paquete_enviar);

                // Arreglo de bytes para recibir los datos
                byte[] bufferEntrada = new byte[1024];

                // Crear paquete para recibir datos
                DatagramPacket paquete_recibir = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                // Recibir paquete
                socket.receive(paquete_recibir);

                // Extraer la información del paquete recibido
                String mensajeRecibido = new String(paquete_recibir.getData(), 0, paquete_recibir.getLength());
                System.out.println("Mensaje recibido del cliente: " + mensajeRecibido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
