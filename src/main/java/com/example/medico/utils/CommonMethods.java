package com.example.medico.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class CommonMethods {

	
	public static boolean saveImage(String bImage, File file) {
		String beforeImage = getImageData(bImage);
		System.err.println(file+"7777777777777777777777777777777777777777");
		
		byte[] bArray = Base64.getDecoder().decode(beforeImage.getBytes());
		File folder = new File(Constants.PATH);
		if (!folder.exists())
		    System.out.println(folder.mkdirs());
		try {
			System.err.println("afetrere  rrrrrrrrrrrrrrrrrrrrr");
			 new FileOutputStream(file).write(bArray);
				System.err.println("afetrere  rrrrrrrrrrrrrrrrrrrrr");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static String getImageData(String beforeImage) {
		return beforeImage.split(",")[1];

	}

}
