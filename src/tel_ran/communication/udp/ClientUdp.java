package tel_ran.communication.udp;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class ClientUdp {
	private static final int TIMEOUT = 5000;
	private static final int MAX_LENGTH = 1500;
	InetAddress addressServer;
	int portServer;
	DatagramSocket socket;
	String request;
	private int repeats;

	public ClientUdp() {
	}

	public ClientUdp(String hostName, int portServer, int repeats) throws UnknownHostException, SocketException {

		this.repeats = repeats;
		this.addressServer = InetAddress.getByName(hostName);
		this.portServer = portServer;
		socket = new DatagramSocket();
		socket.setSoTimeout(TIMEOUT);
	}

	public ClientUdp(String hostname, int portServer) throws UnknownHostException, SocketException {
		this(hostname, portServer, Integer.MIN_VALUE);
	}

	public void send(String request) throws IOException {
		this.request = request;
		byte[] buf = request.getBytes();
		DatagramPacket spacket = new DatagramPacket(buf, buf.length, addressServer, portServer);
		socket.send(spacket);
	}

	public byte[] receive() throws IOException {
		byte[] buf = new byte[MAX_LENGTH];
		DatagramPacket rpacket = new DatagramPacket(buf, MAX_LENGTH);

		for (int i = 0; i < repeats; i++) {
			try {
				socket.receive(rpacket);
				return Arrays.copyOf(buf, rpacket.getLength());
			} catch (SocketTimeoutException e) {
				send(request);
			}
		}
		return null;
	}

	public InetAddress getAddressServer() {
		return addressServer;
	}

	public void setAddressServer(InetAddress addressServer) {
		this.addressServer = addressServer;
	}

	public int getPortServer() {
		return portServer;
	}

	public void setPortServer(int portServer) {
		this.portServer = portServer;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getRepeats() {
		return repeats;
	}

	public void setRepeats(int repeats) {
		this.repeats = repeats;
	}

	public void setHostName(String hostName) throws UnknownHostException {
		this.addressServer = InetAddress.getByName(hostName);
	}

}
