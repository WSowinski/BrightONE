package brightonetask1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Wojciech Sowiński
 */
public class BrightOneTask1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Getting data from file
        BufferedReader input = new BufferedReader(new FileReader("data.txt"));
        String inputData = "";
        String buffer = "";
        // Reads whole file and append to one string
        while ((buffer = input.readLine()) != null) {
            inputData += buffer;
        }

        // Deletes any non hex char
        String outputData = inputData.replaceAll("[^0-9A-Fa-f]", "");
        // Converting String data to array of bytes
        byte[] byteArray = hexStringToByteArray(outputData);
        
        ArrayList<Byte> evenBytes = new ArrayList<Byte>();
        ArrayList<Byte> oddBytes = new ArrayList<Byte>();
        
        for (byte b : byteArray) {
            if(evenNumberOfOnes(b)){
                evenBytes.add(b);
            }
            else{
                oddBytes.add(b);
            }
        }
        
        Collections.sort(evenBytes);
        Collections.sort(oddBytes, Collections.reverseOrder());
        
        
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");
        writer.println(evenBytes);
        writer.println(oddBytes);
        
        writer.close();
    }

    private static byte[] hexStringToByteArray(String s) {
        int length = s.length();
        // checking if number of characters are even if not adds 0 to second to last
        if ((length & 1) == 1) {
            s = s.substring(0, length-1) + "0" + s.substring(length-1);
            length++;
        }
        
        byte[] data = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }
    
    private static boolean evenNumberOfOnes(byte b){
        int sumOfOnes = 0;
        int d = 1;
        // Counting ones in binary format
        for (int i = 0; i < 8; i++) {
            if ((b & d) == 1) {
                sumOfOnes++;
            }
            d = d*2;
        }
        
        // Checks if sum is even
        if ((sumOfOnes & 1) == 0) {
            return true;
        }
        else{
            return false;
        }
    }
}
