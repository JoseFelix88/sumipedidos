SELECT

ordenes.producto,
sum(ordenes.cantidad) AS cantidadPedida
FROM `ordenes`
WHERE
ordenes.estado = 1
GROUP BY
ordenes.producto
ORDER BY
ordenes.producto ASC
