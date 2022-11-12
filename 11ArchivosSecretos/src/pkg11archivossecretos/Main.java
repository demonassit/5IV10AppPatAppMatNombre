/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11archivossecretos;

/**
 *
 * @author alumno
 */

import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;

public class Main {
    
    private static Cipher rsa;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
        
        KeyPair llavesRSA = generadorRSA.generateKeyPair();
        
        PublicKey llavePublica = llavesRSA.getPublic();
        
        PrivateKey llavePrivada = llavesRSA.getPrivate();
        
        //metodo para salvar y recuperar archivos
        
        save(llavePublica, "publickey.key");
        
        llavePublica = loadPublicKey("publickey.key");
        
        save(llavePrivada, "privatekey.key");
        
        llavePrivada = loadPrivateKey("privatekey.key");
        
        rsa = Cipher.getInstance("RSA/ECB/PCKS1Padding");
        
        String texto = "hola habia una vez un patito, que queria mimir, y luego vio a god of war ragnarock y estaba baratito, pero derrepente los malditos de internet le subieron el precio asi bien feo hasta 10799 y el patito chillo fin";
        
        //ciframos
        
        rsa.init(Cipher.ENCRYPT_MODE, llavePublica);
        
        byte[] encriptado = rsa.doFinal(texto.getBytes());
        
        //vamos a escribir el cifrado para que sea visible
        
        for(byte b : encriptado){
            System.out.print(Integer.toHexString(0xFF & b));
        }
        
        System.out.println("");
      
        //deciframos
        rsa.init(Cipher.DECRYPT_MODE, llavePrivada);
        
        System.out.println("");
        
        byte[] bytesdescifrado = rsa.doFinal();
        String textodescifrado = new String(bytesdescifrado);
        System.out.println(textodescifrado);
    }

    private static void save(Key key, String archivo) throws Exception{
        byte[] llavesbytes = key.getEncoded();
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavesbytes);
        fos.close();
    }

    private static PublicKey loadPublicKey(String archivo) throws Exception{
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        KeyFactory llaveBytesPublico = KeyFactory.getInstance("RSA");
        KeySpec keyspec = new X509EncodedKeySpec(bytes);
        PublicKey nuevallavepublica = 
                llaveBytesPublico.generatePublic(keyspec);
        return nuevallavepublica;
    }

    private static PrivateKey loadPrivateKey(String archivo) throws Exception{
        FileInputStream fis = new FileInputStream(archivo);
        int numBytes = fis.available();
        byte[] bytes = new byte[numBytes];
        fis.read(bytes);
        fis.close();
        
        KeyFactory llaveBytesPrivado = KeyFactory.getInstance("RSA");
        KeySpec keyspec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey nuevallaveprivada = 
                llaveBytesPrivado.generatePrivate(keyspec);
        return nuevallaveprivada;
    }
    
}
