package com.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.controller.PDFConverter;


@Service
public class PDFGeneratorService {
	
	@Autowired
	PDFConverter pdfConverter;
	
	public String  ConvertPdf() throws Exception
	{
		 Map<String,String> data = new HashMap<String,String>();
		    data.put("name","Marc");
		  return  pdfConverter.createPdf("email2",data); 
	}

}
