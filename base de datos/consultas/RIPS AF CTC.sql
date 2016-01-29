SELECT
empresas.Nit,
empresas.Nombre,
'NI',
empresas.Nit,
puntos.PREFIJO,
ordenes.fechaingreso,
'2015-12-01' AS FECHAINICIO,
'2015-12-31' AS FECHAFINAL,
clientes.codigo_prest AS CODCLIENTE,
clientes.nombre AS CLIENTE,
'S-CE-001-15A' AS NUMCONTRATO,
'SUBSIDIADO' AS REGIMEN,
'' AS VACIO1,
0 AS COPAGO,
'' AS VACIO2,
0 AS descuento,
Sum(ordenes.cantidad*productosbase.precio) AS TOTALFACTURA,ordenes.numorden
FROM
clientes ,
empresas ,
ordenes ,
puntos,productosbase
WHERE productosbase.cum = ordenes.cumproducto AND 
clientes.estado = 1 AND
empresas.estado = 1 AND
ordenes.estado = 1 AND
puntos.Tipo = 1 AND
ordenes.puntoentrega = puntos.NOMBRE GROUP BY ordenes.numorden

