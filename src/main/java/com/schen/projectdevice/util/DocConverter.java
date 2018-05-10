package com.schen.projectdevice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocConverter {
	// Format for Microsoft Office 2007 and above
	private final static DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT,
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");

	private final static DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML",
			DocumentFamily.PRESENTATION, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
	private final static DocumentFormat pptx = new DocumentFormat("Microsoft PowePoint 2007",
			DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"pptx");

	public void office2PDF(String inputFile, String outputFile) {

		try {
			File input = new File(inputFile);
			if (!input.exists()) {
				System.out.println("File does not exist");
			}
			File output = new File(outputFile);
			if (output.exists()) {
				output.delete();
			}
			String open_office_home = "/opt/libreoffice5.4/";
			String command = open_office_home
					+ "program/soffice --headless --accept=\"socket,host=127.0.0.1,port=8100;urp;\" --nofirststartwizard";
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			System.out.println("Input: " + inputFile);
			System.out.println("Output: " + outputFile);
			String suffix = inputFile.substring(inputFile.lastIndexOf("."));
			if (suffix.equals(".docx")) {
				converter.convert(input, docx, output, null);
			} else if (suffix.equals(".pptx")) {
				converter.convert(input, pptx, output, null);
			} else if (suffix.equals(".xlsx")) {
				converter.convert(input, xlsx, output, null);
			} else {
				converter.convert(input, output);
			}
			connection.disconnect();
			input.delete();
			process.destroy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void office2pdf(String inputFile, String outputFile) {
		File input = new File(inputFile);
		if (!input.exists()) {
			System.out.println("File does not exist");
		}
		File output = new File(outputFile);
		if (output.exists()) {
			return;
		}
		String outDir = outputFile.substring(0, outputFile.lastIndexOf('/') );
		String open_office_home = "/opt/libreoffice5.4/";
		String command = open_office_home
				+ "program/soffice --headless --convert-to pdf --outdir " + outDir + " " + inputFile;
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			process.destroy();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
