import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP { public static void main(String[] args) {

    try {
        int puerto = 5000; // Número de puerto

        // Crear un socket UDP
        DatagramSocket socket = new DatagramSocket(puerto);
        System.out.println("Cliente esperando conexiones");

        // Dirección IP del servidor
        InetAddress direccionIP_cliente = InetAddress.getByName("172.31.118.82"); // Ajusta la dirección IP del cliente

        // Bucle principal
        while (true) {
            // Leer mensaje desde la terminal del servidor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el mensaje para el servidor: ");
            String mensajeCliente = scanner.nextLine();

            // Enviar mensaje al cliente
            DatagramSocket enviarSocket = new DatagramSocket();
            byte[] bufferSalida = mensajeCliente.getBytes();
            DatagramPacket paquete_enviar = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP_cliente, puerto);
            enviarSocket.send(paquete_enviar);
            enviarSocket.close();

            // Arreglo de bytes para recibir los datos
            byte[] bufferEntrada = new byte[1024];

            // Crear paquete para recibir datos
            DatagramPacket paquete_recibir = new DatagramPacket(bufferEntrada, bufferEntrada.length);

            // Recibir paquete
            socket.receive(paquete_recibir);

            // Extraer la información del paquete recibido
            String mensajeRecibido = new String(paquete_recibir.getData(), 0, paquete_recibir.getLength());
            System.out.println("Mensaje recibido del servidor: " + mensajeRecibido);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
