package com.mx.profuturo.bolsa.util.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;



@Component
public class FileUtil {
	
	@Autowired
	EscapeUtil escapeUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	public String getFileAsString(Resource mockFile) {
		String content = null;
		try {
			
			content = this.getStringFromInputStream(mockFile.getInputStream());
		} catch (IOException e) {
			 LOGGER.error("FileUtil:No se pudo recuperar el archivo");
		}
		return content;
	}
	
	private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            LOGGER.info("IOException err" + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.info("IOException " + e);
                }
            }
        }
        return EscapeUtil.escapeHtml(sb.toString());
    }
}
