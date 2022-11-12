/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsalib;

/**
 *
 * @author alumno
 */

import java.io.*;
import java.security.*;

import javax.crypto.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*
La libreria de BC nos sirve para el calculo de los numeros p q n fi 
aun mas grandotes, para la generacion de llaves publicas y privadas,
mas seguras, pero su desventaja es que no maneja de forma correcta el modo
ECB o el modo a bloques del algoritmo por lo que el tamaño de  la
llave debe de ser de 512 y realizar el bloque a mano

*/


public class RSALib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        //Primero tenemos que añadir el nuevo Proveedor de algotimo debido a que
        //security no tiene soporte para BC
        
        Security.addProvider(new BouncyCastleProvider());
        
        //vamos a la generacion de las claves
        System.out.println("1.- crear las llaves publica y privada");
        
        //la clase KeyPairGenerator
        
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA", "BC");
        
        //iniciliazamos la llave
        
        keygen.initialize(4096);
        
        //vamos asignar la llave publica y privada
        
        KeyPair clavesRSA = keygen.generateKeyPair();
        
        //definimos a la llave privada y publica
        PrivateKey clavePrivada = clavesRSA.getPrivate();
        PublicKey clavePublica = clavesRSA.getPublic();
        
        System.out.println("2.- Introducir el texto plano que desea cifrar (maximo 64 caracteres");
        
        //almacenar el texto en un arreglo de bytes
        
        byte[] bufferplano = leerLinea(System.in);
        
        //ciframos
        
        Cipher cifrado = Cipher.getInstance("RSA", "BC");
        
        //ciframos con publica
        
        cifrado.init(Cipher.ENCRYPT_MODE, clavePublica);
        
        System.out.println("3.- Ciframos con la clave Publica: ");
        
        byte[] bufercifrado = cifrado.doFinal(bufferplano);
        
        System.out.println("Texto Cifrado: " );
        
        //no tiene formato es binario
        mostrarBytes(bufercifrado);
        
        System.out.println("");
        
        //desciframos con privada
        
        cifrado.init(Cipher.DECRYPT_MODE, clavePrivada);
        
        System.out.println("4.- Desciframos con la clave Privada: ");
        
        byte[] bufferdescifrado = cifrado.doFinal(bufercifrado);
        
        System.out.println("Texto Descifrado: ");
        
        mostrarBytes(bufferdescifrado);
        
        System.out.println("");
        
        
        //vamos a voltearlo
        
        
        cifrado.init(Cipher.ENCRYPT_MODE, clavePrivada);
        
        System.out.println("5.- Ciframod con la clave privada: ");
        
        bufercifrado = cifrado.doFinal(bufferplano);
        
        System.out.println("Texto cifrado :");
        
        mostrarBytes(bufercifrado);
        
        System.out.println("");
        
        cifrado.init(Cipher.DECRYPT_MODE, clavePublica);
        
        System.out.println("6.- Desciframos con clave Publica: ");
        
        bufferdescifrado = cifrado.doFinal(bufercifrado);
        
        System.out.println("Texto descifrado :");
        
        mostrarBytes(bufferdescifrado);
        
        System.out.println("");
        
        
    }

    private static byte[] leerLinea(InputStream in) throws Exception{
        //definir como vamos a realizar la lectura del bloque
        byte[] buffer1 = new byte[1000];
        
        int i = 0;
        
        byte c;
        
        c = (byte)in.read();
        
        while((c != '\n') && (i < 1000)){
            buffer1[i] = c;
            c = (byte)in.read();
            i++;
        }
        
        byte[] buffer2 = new byte[i];
        for(int j = 0; j < i; j++){
            buffer2[j] = buffer1[j];
            
        }
        return buffer2;
    }

    private static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
    
}
