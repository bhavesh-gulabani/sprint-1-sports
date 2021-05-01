package com.cg.util;

import java.io.IOException;
import java.util.Arrays;

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
    		long productId;

//    		if (tokens.length < 3) {
//    			System.out.println(key.substring(12, 15));
//    			productId = Long.parseLong(key.substring(12, 15));
//    		} else {
//    			productId = Long.parseLong(tokens[3]);
//    		}
    		System.out.println(key);
    		System.out.println(Arrays.toString(tokens));
    		
    		productId = Long.parseLong(tokens[3]);
    		
    		System.out.println(productId);
			return prodService.getProductById(productId);
    		
    	} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}