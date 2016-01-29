SELECT
puntos.PREFIJO,
empresas.Nit,
afiliados.TIPODOC,
afiliados.DOCUMENTO,
ordenes.numorden,
productosbase.cum,
pos_no_pos(productosbase.cum) as pos,
productosbase.descripcion,
productosbase.cantidadconcentrada,
productosbase.unidadmedida,
productosbase.formafarmaceutica,
ordenes.cantidad,
productosbase.precio AS vrUnidad,
ordenes.cantidad*
productosbase.precio AS total
FROM
afiliados ,
ordenes ,
empresas ,
productosbase ,
puntos
WHERE
ordenes.estado = 1 AND
empresas.estado = 1 AND

afiliados.DOCUMENTO = ordenes.idafiliado AND
ordenes.cumproducto = productosbase.cum AND
ordenes.puntoentrega = puntos.NOMBRE
