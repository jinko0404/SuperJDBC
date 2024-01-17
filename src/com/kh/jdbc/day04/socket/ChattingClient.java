package com.kh.jdbc.day04.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChattingClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String address = "127.0.0.1";
		int port = 9999;
		InputStream is = null;
		DataInputStream dis = null;
		OutputStream os = null;
		DataOutputStream dos = null;
		String sendMsg = "";
			try {
				System.out.println("서버와 연결 중 입니다.");
				@SuppressWarnings("resource")
				Socket socket = new Socket(address, port);
				System.out.println("서버와 연결되었습니다.");
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				while(true) {
					String recvMsg = dis.readUTF();
					if(recvMsg.equalsIgnoreCase("exit")) {
						System.out.println("서버에서 종료하였습니다.");
						break;
					}
					System.out.println("서버(상대) : " + recvMsg);
					System.out.print("클라이언트(나) : ");
					sendMsg = sc.nextLine();
					dos.writeUTF(sendMsg);
					dos.flush();
					if(sendMsg.equalsIgnoreCase("exit")) {
						System.out.println("채팅을 종료하였습니다.");
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            sc.close();
	        }
	}

}
