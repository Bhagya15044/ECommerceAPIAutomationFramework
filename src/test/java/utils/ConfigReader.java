package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    //to store loaded key-value pairs from the config.properties file acts like centralized configuration storage object
    public static Properties properties = new Properties();
    static
    {
        try
        {
           FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
           properties.load(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }
}
/*
This class becomes:reusable configuration service layer
Its responsibility:
✅ load config file
✅ read properties dynamically
✅ provide config values to framework
WITHOUT hardcoding.

Usage of try block:
Because:
file handling operations may fail.
Examples:
❌ file missing
❌ wrong path
❌ access denied
Java forces us to handle:\
checked exceptions
* To Add the path in the FileInputStream just right click of config.properties->copy path->content path

Usage of the Static block:
Static block executes automatically loads config.properties file once
Because:
we do NOT want:
 ❌ repeated config loading every testcase
 ❌ repeated file opening
instead of that load configuration once globally

ACTUAL CODE FLOW :

ConfigReader acts as a reusable configuration access layer in the framework.
First, we create a centralized Properties object to store all key-value pairs loaded from config.properties.
When the ConfigReader class loads, the static block executes automatically.
Inside the static block, FileInputStream opens the config.properties file using the specified file path.
Then the Properties object loads all configuration key-value pairs into memory.
We also handle exceptions using e.printStackTrace() so that if file loading fails, Java prints complete exception details such as error type, message, and line number for debugging purposes.
Finally, the getProperty() method dynamically returns configuration values as String using their corresponding keys, allowing the framework to access externalized configurations without hardcoding values inside source code.
 */