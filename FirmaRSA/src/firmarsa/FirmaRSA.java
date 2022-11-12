/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmarsa;

import java.security.KeyPairGenerator;

/**
 *
 * @author alumno
 */

import javax.crypto.*;
import java.security.*;

//libreria para los formatos de la firma
import sun.misc.BASE64Encoder;

public class FirmaRSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        //vamos a realizar la instancia correspondiente del algoritmo
        KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
        
        //inicializamos la llave
        generadorRSA.initialize(4096);
        
        //crear las llaves para la firma
        KeyPair llaves = generadorRSA.generateKeyPair();
        
        //ustedes lo hacen con sha y yo con md5 wiiii
        
        //texto
        byte [] dato = "Habia una vez un patito que decia miau miau, y queria mimir y mimir y comer chocolates y tambien queria dos novias, para que lo mantengan y asi ser mas feliz y un play 5 porque eso de god of war no alcanzamos al edicion de coleccion bien bonita con martillo y todo eso por eso necesitamos mas cositas lindas y kawaii".getBytes("UTF8");
        
        //preparamos la firma
        
        Signature firma = Signature.getInstance("MD5WithRSA");
        
        //inicializo la llave privada
        firma.initSign(llaves.getPrivate());
        
        //actualizamos el documento
        firma.update(dato);
        
        //ahora firmamos el documento
        byte[] firmadocumento = firma.sign();
        
        System.out.println("La firma digital es: " + new BASE64Encoder().encode(firmadocumento));
 
        
        //proceso de verificacion de la firma
        firma.initVerify(llaves.getPublic());
        
        //vamos a actualizar el edo del documento
        firma.update(dato);
        
        //vamos a ver si esta bien el doc, si es valido
        System.out.println("¿El documento es valido?");
        System.out.println(firma.verify(firmadocumento));
        
        
    }
    
}
