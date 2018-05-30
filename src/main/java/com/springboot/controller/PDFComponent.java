package com.springboot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PDFComponent {

	@Autowired
	private TemplateEngine templateEngine;

	
	public File createPdf(String templateName,String fileName, Map<String, Object> map) throws Exception {
		
		File pdf = null;
		
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
		
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf");
			fileName = outputFile.getName();

			pdf = outputFile.getAbsoluteFile();

			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return pdf;
	}
}
