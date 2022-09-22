/**vamos a crear una funcion recursiva que se encargue de  mandar a llamar a la funcion como un objeto en caso de que no se pueda ejecutar */

var cesar = cesar || (function(){
    var proceso = function(txt, desp, action){
        var replace = (function(){
        //primero necesito una matriz del abc
        var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        var l = abc.length;

        /* necesitamos una funcion que pueda obtener la posicion que va  avenir por parte de la clave privada o desplazamiento */

        return function(c){
            //necesitamos saber la posicion 
            var i = abc.indexOf(c.toLowerCase());

            /* Necesitamos saber donde estamos dentro de la matriz abc y como la vamos a recorrer para el momento del cifrado */
            if(i != -1){
                //primero obtenemos la poscion para el desp
                var pos = i;
                //necesito saber la operacion a realizar c o d
                if(action){
                    //cifrar hacia adelante
                    pos+=desp;
                    //definir como se va a mover
                    pos -= (pos >= l)?1:0;
                }else{
                    //descifra para atras
                    pos-=desp;
                    //movimiento
                    pos += (pos < 0)?1:0;
                }
                return abc[pos];
            }
            return c;
        };
    })();
    //tenemos que saber que el texto este acorde abc

    var re = (/([a-z])/ig);

    //una funcion que se encargue del intercambio de las posciones acorde a si coincide el texto a cifrar con la expresion regular

    return String(txt).replace(re, function(match){
        return replace(match);
    });

    };

    return{
        encode : function(txt, desp){
            return proceso(txt, desp, true);
        },
        decode : function(txt, desp){
            return proceso(txt, desp, false);
        }
    };

})();

function cifrar(){
    document.getElementById("resultado").innerHTML = cesar.encode(document.getElementById("cadena").value, 3);
}

function descifrar(){
    document.getElementById("resultado").innerHTML = cesar.decode(document.getElementById("cadena").value, 3);
}