/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg07rsamanita;

import java.math.BigInteger;
import java.util.*;
import java.io.*;



/**
 *
 * @author alumno
 */
public class RSAManita {

    /**
     * @param args the command line arguments
     */
    
    
    //declaramos nuestras variables grandotas
    int tamprimo;
    BigInteger p, q, n;
    BigInteger fi;
    BigInteger e, d;
    
    //constructor
    public RSAManita(int tamprimo){
        this.tamprimo = tamprimo;
    }
    
    
    //el metodo para generar los numeros primos p q
    public void generarPrimos(){
        p = new BigInteger(tamprimo, 10, new Random());
        //el otro primo debe de ser diferente de p
        do q = new BigInteger(tamprimo, 10, new Random());
            while (q.compareTo(p)==0);
    }
    
    //vamos a generar las llaves e d
    public void generarClave(){
        // n = p*q
        //fi = (p-1) * (q-1)
        n = p.multiply(q);
        
        fi = p.subtract(BigInteger.valueOf(1));
        
        fi = fi.multiply((q.subtract(BigInteger.valueOf(1))));
        
        //calcular e y d
        // debe de ser un coprimo tal que 1 < e < fi(n)
        
        do e = new BigInteger(2*tamprimo, new Random());
            while ((e.compareTo(fi) != -1  )
                || (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0));
        
        //calcular d es el inverso multiplicativo de e
        // d = e ^ 1 mod fi
        
        d = e.modInverse(fi);
    }
    
    //cifrar
    public BigInteger[] cifrar(String mensaje){
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        
        BigInteger[] bigdigitos = 
                new BigInteger[digitos.length];
        
        //recorro el arreglo
        for(i = 0; i < bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }
        
        //aplico el cifrado
        BigInteger[] cifrado = 
                new BigInteger[bigdigitos.length];
        
        //aplico la formula  c = M ^ e * mod n
        for(i = 0; i < bigdigitos.length; i++){
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }
        return cifrado;
    }
    
    
    //descifrar
    public String descifrar(BigInteger[] cifrado){
        int i;
        BigInteger[] descifrado = 
                new BigInteger[cifrado.length];
        
        //descifrar   Md = C ^ d * mod n
        
        //recorro el arreglo
        for(i = 0; i < descifrado.length; i++){
            descifrado[i] = cifrado[i].modPow(d, n);
        }
        
        //formato
        char[] charArray = new char[descifrado.length];
        
        //genero mi cadena
        for(i = 0; i < charArray.length; i++){
            charArray[i] = (char)(descifrado[i].intValue());
        }
        return (new String(charArray));
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        /*
    Wiiiiiiiiiiiiii listo
    
    Asi que mi venganza wiiiiiiiiiiiiiiiii *w* n_n
    
    Tarea
    
    Opcion 1:
   
    Realizar este programa de calculo de RSA  
        con una hermosa
    ventana en swing usando metodos privados 
        (1 persona) max 3 digitos los primos
   
   
    Opcion 2:
   
    Realizar este mismo algoritmo con un JSP 
        y un servicio web en el cual
    se tiene un formulario y se agregan los 
        correspondientes elementos
    [con request y response] se hacen las 
        operaciones [1 persona] maximo 3 digitos
   
   
   
    Opcion 3:
    Realizar un cliente y un servidor
   
    Cliente calcula sus numeros p y q n fi
    Servidor calcula sus numeros p q n fi
   
    El equipo se pone de acuerdo para saber quien cifra y quien descifra los mensjaes
    de numeros en un chat
   
    2 personas maximo 3 digitos
    
    
    */
    }
    
}
