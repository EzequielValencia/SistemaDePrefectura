package paqueteDatos;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoServidor mimarco=new MarcoServidor();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}	
}

class MarcoServidor extends JFrame implements Runnable,WindowListener{

	private static final long serialVersionUID = 1L;
	private boolean servidorCorriendo;
	ServerSocket servidor;
	public MarcoServidor(){

		servidorCorriendo = true;
		setBounds(1200,300,280,350);				

		JPanel milamina= new JPanel();

		milamina.setLayout(new BorderLayout());

		areatexto=new JTextArea();
		JScrollPane scrollAreaTexto = new JScrollPane(areatexto);
		milamina.add(scrollAreaTexto,BorderLayout.CENTER);

		add(milamina);

		setVisible(true);
		Thread hilo = new Thread(this);
		hilo.start();
	}

	private	JTextArea areatexto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			servidor = new ServerSocket(9999);
			while(servidorCorriendo){
				

				Socket enviadorDatos;
				ObjectOutputStream objetoSalida;
				Socket socket = servidor.accept();
				ObjectInputStream flujoDeEntrada = new ObjectInputStream(socket.getInputStream());

				DatosEntradaSalida datosEntrada = (DatosEntradaSalida) flujoDeEntrada.readObject();
				
				if(datosEntrada != null){
					System.out.println("Ip Destno "+datosEntrada.getIpDestino());
					String textoEntrada = datosEntrada.getRemitente()+": "+datosEntrada.getMensaje();
					System.out.println("En el socket "+textoEntrada);
					areatexto.append("\n"+textoEntrada);
					
					enviadorDatos = new Socket(datosEntrada.getIpDestino(), 9090);
					objetoSalida = new ObjectOutputStream( enviadorDatos.getOutputStream() );
					objetoSalida.writeObject(datosEntrada);
					
					datosEntrada = null;
					System.out.println(datosEntrada);
					enviadorDatos.close();
					socket.close();
					
				}



			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		servidorCorriendo = false;
		try {
			servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}

