create database Cotizacion;
use Cotizacion;

select * from Cotizacion.usuario;
select * from Cotizacion.producto;
select * from Cotizacion.cotizacion;

select cot.id_cotizacion, cot.fecha, usu.nombre as usuario, pro.nombre as producto 
from cotizacion as cot  
inner join usuario as usu on cot.id_usuario = usu.id_usuario  
inner join producto as pro on cot.codigo_producto = pro.codigo_producto;
