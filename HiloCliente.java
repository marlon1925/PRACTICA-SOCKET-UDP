import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloCliente extends Thread {
    private DatagramSocket socket;
    private DatagramPacket paquete;

    public HiloCliente(DatagramSocket socket, DatagramPacket paquete) {
        this.socket = socket;
        this.paquete = paquete;
    }

    @Override
    public void run() {
        try {
            // Extraer la información del paquete recibido
            String mensajeRecibido = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("Mensaje recibido desde el cliente: " + mensajeRecibido);

            // Puedes procesar el mensaje y preparar una respuesta
            String respuesta = "Hola, soy el servidor";
            byte[] bufferRespuesta = respuesta.getBytes();

            // Obtener la dirección IP y el puerto del cliente
            InetAddress direccionCliente = paquete.getAddress();
            int puertoCliente = paquete.getPort();

            // Crear paquete para enviar la respuesta al cliente
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);

            // Enviar paquete de respuesta al cliente
            socket.send(paqueteRespuesta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
