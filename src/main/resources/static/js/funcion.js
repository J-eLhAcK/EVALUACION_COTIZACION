$(document).ready(function() {
  const usuariosListados = document.querySelector(".usuariosListados");
  // usuariosListados.innerHTML="<option value='1'>|-Nombre--Email-|</option>"

  $.ajax({
    url: "http://localhost:8080/ListarUsuarios",
    type: "GET",
    datatype: "JSON",
    success: function(response) {
      Object.values(response).forEach(usuario => {
        usuariosListados.innerHTML +=
          '<option value="' +
          usuario["idUsuario"] +
          '">' +
          usuario["nombre"] +
          " -- " +
          usuario["email"] +
          "</option>";
      });
    }
  });

  const seleccionPro = document.querySelector(".seleccionPro");
  // seleccionPro.innerHTML="<option value='1'>|-Nombre--Precio-|</option>"

  $.ajax({
    url: "http://localhost:8080/Listarproductos",
    type: "GET",
    datatype: "JSON",
    success: function(response) {
      Object.values(response).forEach(producto => {
        seleccionPro.innerHTML +=
          '<option value=" ' +
          producto["codigo_producto"] +
          ' ">' +
          producto["nombre"] +
          " -- $" +
          producto["precio"] +
          "</option>";
      });
    }
  });

  const seleccionCot = document.querySelector(".seleccionCot");
  // seleccionCot.innerHTML="<option value='1'>|-Fecha--USUARIO(FK)-PRODUCTO(FK)-|</option>"

  $.ajax({
    url: "http://localhost:8080/obtenerDetallesCotizaciones",
    type: "GET",
    datatype: "JSON",
    success: function(response) {
      Object.values(response).forEach(cotizacion => {
        seleccionCot.innerHTML +=
          '<option value=" ' +
          cotizacion["id_cotizacion"] +
          ' ">' +
          cotizacion["fecha"] +
          " -- " +
          cotizacion["usuario"] +
          " -- " +
          cotizacion["producto"] +
          "</option>";
      });
    }
  });

  // Buscar Cotización por ID
  $(".buscarForm").submit(function(event) {
    event.preventDefault();

    var idCotizacion = $(".idCotizacion").val();

    $.ajax({
      type: "GET",
      url: "http://localhost:8080/buscarCotizaciones/" + idCotizacion,
      success: function(response) {
        if (response != null) {
          // La cotización se encontró, puedes procesar los datos de la cotización aquí
          var cotizacionHTML = '<tr>' +
            '<td>' + response.idCotizacion + '</td>' +
            '<td>' + response.cantSolicitada + '</td>' +
            '<td>' + response.idMoneda + '</td>' +
            '<td>' + response.fecha + '</td>' +
            '</tr>';

          $(".resultadoCotizacion tbody").html(cotizacionHTML);
        } else {
          alert("No se encontró la cotización con la ID especificada.");
        }
      },
      error: function(error) {
        alert("Error al buscar la cotización: " + error.responseText);
      }
    });
  });



  // Agregar Cotización
  $(".agregarForm").submit(function(event) {
    event.preventDefault();

    var idUsuario = $(".idUsuario").val();
    var codigoProducto = $(".codigoProducto").val();
    var cantSolicitada = $(".cantSolicitada").val();
    var idMoneda = $(".idMoneda").val();

    var data = {
      cantSolicitada: cantSolicitada,
      idMoneda: idMoneda
    };

    $.ajax({
      type: "POST",
      url: "http://localhost:8080/agregarCotizacion/" + idUsuario + "/" + codigoProducto,
      data: data,
      success: function(response) {
        alert(response);
      },
      error: function(error) {
        alert("Error al agregar la cotización: " + error.responseText);
      }
    });
  });


  // Agregar Cotización
  //$(".agregarForm").submit(function(event) {
  //  event.preventDefault();
//
  //  var idUsuario = $(".idUsuario").val();
  //  var codigoProducto = $(".codigoProducto").val();
  //  var cantSolicitada = $(".cantSolicitada").val();
  //  var idMoneda = $(".idMoneda").val();
//
  //  var data = {
  //    idUsuario: idUsuario,
  //    codigoProducto: codigoProducto,
  //    cantSolicitada: cantSolicitada,
  //    idMoneda: idMoneda
  //  };
//
  //  $.ajax({
  //    type: "POST",
  //    url: "http://localhost:8080/agregarCotizacion/"+idUsuario+"/"+codigoProducto+"?cantSolicitada="+cantSolicitada+"&idMoneda="+idMoneda,
  //    data: data,
  //    success: function(response) {
  //      alert(response);
  //    },
  //    error: function(error) {
  //      alert("Error al agregar la cotización: " + error.responseText);
  //    }
  //  });
  //});

  // Actualizar Cotización
  $(".actualizarForm").submit(function(event) {
    event.preventDefault();

    var idCotizacion = $(".idCotizacion").val();
    var cantSolicitada = $(".nuevaCantSolicitada").val();
    var idMoneda = $(".nuevoIdMoneda").val();

    var data = {
      cantSolicitada: cantSolicitada,
      idMoneda: idMoneda
    };

    $.ajax({
      type: "PUT",
      url: "http://localhost:8080/actualizarCotizacion/" + idCotizacion,
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function(response) {
        alert("Cotización actualizada exitosamente.");
      },
      error: function(error) {
        alert("Error al actualizar la cotización: " + error.responseText);
      }
    });
  });

  // Eliminar Cotización
  $(".eliminarForm").submit(function(event) {
    event.preventDefault();

    var idCotizacion = $(".nuevaidCotizacion").val();

    $.ajax({
      type: "DELETE",
      url: "http://localhost:8080/eliminarCotizacion/" + idCotizacion,
      success: function(response) {
        if (response) {
          alert("Cotización eliminada exitosamente.");
        } else {
          alert("No se encontró la cotización con la ID especificada.");
        }
      },
      error: function(error) {
        alert("Error al eliminar la cotización: " + error.responseText);
      }
    });
  });

});
