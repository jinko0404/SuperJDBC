package com.kh.jdbc.day04.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChattingServer {

	public static void main(String[] args) {
		//네트워크 프로그래밍
		Scanner sc = new Scanner(System.in);
		ServerSocket serverSocket = null;
		int port = 9999;
		OutputStream os = null;
		DataOutputStream dos = null;
		InputStream is = null;
		DataInputStream dis = null;
		String sendMsg = "";
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("클라이언트의 응답을 기다리고 있습니다...");
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트와 접속되었습니다.");
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				while(true) {
					System.out.print("서버(나) : ");
					sendMsg = sc.nextLine();
					dos.writeUTF(sendMsg);
					dos.flush();
					if(sendMsg.equalsIgnoreCase("exit")) {
						System.out.println("채팅을 종료하였습니다.");
						break;
					}
					
					String recvMsg = dis.readUTF();
					if(recvMsg.equalsIgnoreCase("exit")) {
						System.out.println("클라이언트에서 종료하였습니다.");
						break;
					}
					System.out.println("클라이언트(상대) : " + recvMsg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            sc.close();
	        }
			sc.close();
	}

}
