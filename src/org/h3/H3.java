package org.h3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class H3 {
	
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.out.println("Usage: java -jar H3.jar file.dll");
			return;
		}
		
		try {
			byte[] bData = readSmallBinaryFile(args[0]);
			
			encodeData(bData);
			
			writeSmallBinaryFile(bData, args[0]);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static byte[] readSmallBinaryFile(String fileName) throws IOException {
	    Path path = Paths.get(fileName);
	    return Files.readAllBytes(path);
	}
	
	private static void writeSmallBinaryFile(byte[] aBytes, String fileName) throws IOException {
	    Path path = Paths.get(fileName+".encrypted");
	    Files.write(path, aBytes);
	}
	
	private static void encodeData(byte [] aBytes) {
		if (null == aBytes || aBytes.length == 0)
			return;
		
		int v6 = 0;
		int v7 = 0;
		int a2 = aBytes.length;
		do {
			v7 = (8121 * v7 + 28411) % 0x20D38;
			aBytes[v6] ^= v7;
			v6++;
		}
		while (v6 < a2);
	}
}
