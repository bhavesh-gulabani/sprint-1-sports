package com.cg.util;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.bean.Product;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

// key pattern => "product : {'id' : '123'}"

public class CustomDeserializer extends KeyDeserializer {
    
	@Autowired
	IProductService prodService;
	
	@Override
    public Product deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		// Using the string key here to return a real map key object
    	String[] tokens = key.split("'");
    	try {
			return prodService.getProductById(Long.parseLong(tokens[3]));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}