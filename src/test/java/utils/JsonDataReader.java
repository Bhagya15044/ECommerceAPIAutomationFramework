package utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader
{
    public static Object[][] getUserData()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode users;
        try
        {
            users = objectMapper.readTree(
                    new File("src/test/resources/testdata/users.json"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        int size = users.size();

        Object[][] data = new Object[size][3];
        for(int i = 0; i < size; i++)
        {
            data[i][0] = users.get(i).get("email").asText();

            data[i][1] = users.get(i).get("username").asText();

            data[i][2] = users.get(i).get("password").asText();
        }
        return data;
    }
}

/*
1.What is JsonNode?
JsonNode is a Jackson class used to temporarily hold and navigate JSON data.
It helps us read, access, and extract values from JSON files before converting them into the required format.

2.Why did you use readTree()?
Answer:
We use ObjectMapper.readTree() to read and parse the JSON file into a JsonNode object.
This allows us to access and extract the JSON data programmatically before converting it into Object[][] format required by TestNG DataProvider.

3.What is ObjectMapper?
ObjectMapper is a Jackson class used to read, write, and convert JSON data.
It is commonly used for serialization (Java Object to JSON), deserialization (JSON to Java Object),
and reading JSON files into JsonNode structures for further processing.

Why did you use users.size()?
Answer:
users.size() returns the number of JSON records present in the file.
We use it to dynamically create the Object[][] array so that the framework can handle any number of test data records without hardcoding the array size.
 */