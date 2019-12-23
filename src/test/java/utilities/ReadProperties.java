package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	 
    private Properties properties;
    
    public ReadProperties(){     	
        try {
            this.properties = new Properties();   
            FileInputStream fileInputStream = new FileInputStream("resources/config.properties");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    //Method to retrieve specified property value from properties file
    public String getPropertyValue(String key){
        return this.properties.getProperty(key);
    }
 
    public static void main(String args[]){         
       /* ReadProperties readProperties = new ReadProperties();
        System.out.println("URL  : "+readProperties.getPropertyValue("url"));*/
    }
}