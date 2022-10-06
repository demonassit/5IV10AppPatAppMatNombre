
var mensaje = "Habia una vez un patito que decia miau miau, y queria mimir todo el dia, pero no lo dejaban entonces chillaba y por eso tronaba gente wiiii";

var password = "qwertyui";

//ahora vamos a cifrar

var cifrado = CryptoJS.AES.encrypt(mensaje, password);
var descifrado = CryptoJS.AES.decrypt(cifrado, password);

//uff que largo

document.getElementById("demo00").innerHTML = mensaje;
document.getElementById("demo01").innerHTML = cifrado;
document.getElementById("demo02").innerHTML = descifrado;
document.getElementById("demo03").innerHTML = descifrado.toString(CryptoJS.enc.Utf8);