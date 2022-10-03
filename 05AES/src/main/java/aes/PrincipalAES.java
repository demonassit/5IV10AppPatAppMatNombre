/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

/**
 *
 * @author alumno
 */
public class PrincipalAES {
    
    public static void main(String[] args)throws Exception{
        String mensaje = "habia una vez un patito que decia miau miau, y tenia mucho sueñito, "
                + "y queria mimir, pero era viernes y le iban a dejar tarea,"
                + "asi bien fea fea feota, de esas que desvelan para que entren , "
                + "en modo reserva y porque sigo existiendo dios";
        String mensajeCifrado = AES.cifrar(mensaje);
        String mensajeDescifrado = AES.descifrar(mensajeCifrado);
        
        System.out.println("El mensaje a cifrar es: " + mensaje);
        System.out.println("El mensaje cifrado es: " + mensajeCifrado);
        System.out.println("El mensaje descifrado es: " + mensajeDescifrado);
    }
    
}
