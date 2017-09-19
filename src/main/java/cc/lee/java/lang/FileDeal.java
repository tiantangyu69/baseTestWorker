package cc.lee.java.lang;

import com.google.common.collect.Sets;

import java.io.*;
import java.net.URL;
import java.util.Set;

/**
 * Created by bjlizhitao on 2017/7/24.
 */
public class FileDeal {
    public void dealFile() {
        Set<String> userEmails = Sets.newLinkedHashSet();

        URL url = this.getClass().getClassLoader().getResource("dupusers.txt");


        try {
            System.out.printf(url.getPath());

            FileInputStream fileInputStream = new FileInputStream(url.getPath());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                userEmails.add(line);
            }


            bufferedReader.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String email : userEmails) {
            System.out.println(email);
        }
    }

    public static void main(String[] args) {
        FileDeal fileDeal = new FileDeal();
        fileDeal.dealFile();
    }
}
