$(document).ready(function(){

const usuariosListados= document.querySelector(".usuariosListados")
//usuariosListados.innerHTML="<option value='1'>|-Nombre--Email-|</option>"


$.ajax({

url:"http://localhost:8080/ListarUsuarios",
type:"GET",
datatype: "JSON",

success:function(response){
Object.values(response).forEach(usuario=>{

usuariosListados.innerHTML+=
'<option value="' + usuario['idUsuario'] + '">' +
    usuario['nombre'] + ' -- ' + usuario['email']+
'</option>'


});


}

})


$(document).ready(function(){

const seleccionPro= document.querySelector(".seleccionPro")
//seleccionPro.innerHTML="<option value='1'>|-Nombre--Precio-|</option>"


$.ajax({

url:"http://localhost:8080/Listarproductos",
type:"GET",
datatype: "JSON",

success:function(response){
Object.values(response).forEach(producto=>{

seleccionPro.innerHTML+=
'<option value=" ' + producto['codigo_producto'] +' ">'+
    producto['nombre'] + ' -- $' + producto['precio']+
'</option>'



});

}
})

})



$(document).ready(function(){

const seleccionCot= document.querySelector(".seleccionCot")
//seleccionCot.innerHTML="<option value='1'>|-Fecha--USUARIO-PRODUCTO-|</option>"


$.ajax({

url:"http://localhost:8080/detallesCotizaciones",
type:"GET",
datatype: "JSON",

success:function(response){
Object.values(response).forEach(cotizacion=>{

seleccionCot.innerHTML+=
'<option value=" ' + cotizacion['id_cotizacion'] +' ">'+
    cotizacion['fecha']+ ' -- '  + cotizacion['usuario'] + ' -- ' + cotizacion['producto']
'</option>'

});

}
})

})






})