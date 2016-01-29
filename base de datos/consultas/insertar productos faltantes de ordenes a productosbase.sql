INSERT INTO productosbase (descripcion, cum, precio, costo )
SELECT
ordenes.producto,
ordenes.cumproducto
,0,0
FROM
ordenes  
WHERE
ordenes.estado = 1 AND ordenes.cumproducto NOT IN (SELECT cum from productosbase GROUP BY cum)
GROUP BY
ordenes.cumproducto
ORDER BY
ordenes.producto ASC
