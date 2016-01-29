SELECT
productosbase.cum,
productosbase.descripcion, precio
FROM
productosbase ,
ordenes
WHERE
productosbase.cum = ordenes.cumproducto AND
ordenes.estado = 1 AND
productosbase.precio = 0
GROUP BY
productosbase.descripcion
ORDER BY
productosbase.descripcion ASC
