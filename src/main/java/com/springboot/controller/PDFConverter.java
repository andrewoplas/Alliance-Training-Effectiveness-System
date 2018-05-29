package com.springboot.controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;




@Component
public class PDFConverter {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	static File pdf=null;
	
	public String createPdf(String templateName, Map map) throws Exception {
		
		String tDir = System.getProperty("java.io.tmpdir");
	
		
		
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
		     Iterator itMap = map.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry pair = (Map.Entry) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
		        
			}	      
		}
		
		String processedHtml = templateEngine.process(templateName, ctx);
		  FileOutputStream os = null;
		  String fileName = "Arabes";//UUID.randomUUID().toString();
	        try {
	        	final File outputFile = File.createTempFile(fileName, ".pdf");
	        
	        	
	        //	final File outputFile = File.createTempFile(fileName, ".pdf", new File("https://bitbucket.org/allianceggates/atesrepository/downloads/"));
	        	fileName=outputFile.getName();
	        		
	        	 pdf=outputFile.getAbsoluteFile();
	        	
	            os = new FileOutputStream(outputFile);
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	           
	         
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
	        return fileName;
	}
	
		public static File returnFile()
		{
			return pdf;
		}
	}


