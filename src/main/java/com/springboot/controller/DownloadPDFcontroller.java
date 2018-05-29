package com.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.service.PDFGeneratorService;

import javax.servlet.http.HttpServletResponse;

import java.io.*;

@Controller
@RequestMapping("/download")
public class DownloadPDFcontroller {

	

	    private static  String FILE_PATH;//= "/tmp/example.pdf";
	    private static final String APPLICATION_PDF = "application/pdf";
	    static String fileName;
	    
	 
	    @Autowired
		PDFGeneratorService pdfGenerator;
	    
	    @Autowired
	    PDFConverter pdfConverter;
	    

	    @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET, produces = APPLICATION_PDF)
	    public @ResponseBody void PDFDownload(HttpServletResponse response) throws Exception {
	    	
	    
	    	
	    	fileName= pdfGenerator.ConvertPdf();
	    	//FILE_PATH = "https://bitbucket.org/allianceggates/atesrepository/downloads/" + fileName + ".pdf";
	    	//FILE_PATH = "C:/Users/COLINA/AppData/Local/Temp/" + fileName;
	     //   File file = getFile();
	    	 File file=	PDFConverter.returnFile();
	        InputStream in = new FileInputStream(file);

	        response.setContentType(APPLICATION_PDF);
	        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        FileCopyUtils.copy(in, response.getOutputStream());
	       
	    }
	    
	    private File getFile() throws FileNotFoundException {
	        File file = new File(FILE_PATH);
	        if (!file.exists()){
	            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
	        }
	        return file;
	    }

	}
