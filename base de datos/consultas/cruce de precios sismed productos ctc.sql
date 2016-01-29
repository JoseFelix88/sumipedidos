SELECT
productosbase.descripcion,
precios.Medicamento,
precios.CUM,
precios.POS,
precios.`$MayComMínimo`,
precios.`$MayComMáximo`,
precios.MayComPrecio,
precios.`$MayInsMínimo`,
precios.`$MayInsMáximo`,
precios.MayInsPrecio
FROM
productosbase ,
precios
WHERE
productosbase.cum = precios.CUM
ORDER BY
 precios.POS, productosbase.descripcion ASC
