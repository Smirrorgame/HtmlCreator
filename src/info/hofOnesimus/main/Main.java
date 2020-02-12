package info.hofOnesimus.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import info.hofOnesimus.visual.Frame;

public class Main {
	
	private Frame frame;
	public static final String STD_FILE = "Aktuelles.html";
	public static final int STD_SIZE = 1000;
	
		public Main() {
			frame = new Frame(this);
		}
		
		private static String readLineByLineJava8(String filePath) 
		{
		    StringBuilder contentBuilder = new StringBuilder();
		    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
		    {
		        stream.forEach(s -> contentBuilder.append(s).append("\n"));
		    }
		    catch (IOException e) 
		    {
		    	System.err.println("Datei nicht gefunden!!");
		        e.printStackTrace();
		    }
		    return contentBuilder.toString();
		}
		
		
	public void export(String htmlContent, File file) {		
		String[] htmlString = readLineByLineJava8(file.getAbsolutePath()).split("<!-- Aufteilung -->");
		
		String eingabe= "<!-- Aufteilung -->"+
			"\n<div class=\"box\">\n";
		
		eingabe+=htmlContent;
		eingabe+="</div>";
		
		htmlString[0]+=eingabe+"<hr>";
		htmlString[htmlString.length-1]="<!-- Aufteilung -->"+htmlString[htmlString.length-1];
		for(int i=1;i<htmlString.length; i++) {
			htmlString[0]+=htmlString[i];
		}
					
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)){
			writer.write(htmlString[0]);
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		new Main();
	}

}
