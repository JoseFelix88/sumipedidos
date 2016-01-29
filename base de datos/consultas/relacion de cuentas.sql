SELECT CONCAT('CTC',puntos.COD_PUNTO,YEAR(ordenes.fechaorden))AS NUMEROCTA,
puntos.NOMBRE as puntoentrega,
Count(DISTINCT ordenes.numorden) AS facturas,
Count( ordenes.cumproducto) AS productos,
CONCAT(puntos.PREFIJO,'-',MIN(facturasasignadas.numfactura)) AS inicia,
CONCAT(puntos.PREFIJO,'-',MAX(facturasasignadas.numfactura)) AS finaliza,
totalizarcuenta(ordenes.puntoentrega) as totalcuenta
FROM
ordenes ,
puntos ,
productosbase,facturasasignadas
WHERE
facturasasignadas.puntoentrega = puntos.NOMBRE  AND 
ordenes.puntoentrega = puntos.NOMBRE AND
ordenes.cumproducto = productosbase.cum AND
ordenes.estado = 1
GROUP BY
puntos.NOMBRE
