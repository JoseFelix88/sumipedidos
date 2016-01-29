SELECT
afiliados.TIPODOC,
afiliados.DOCUMENTO,
clientes.codigo_prest,
afiliados.regimen,
afiliados.APE1,
afiliados.APE2,
afiliados.NOM1,
afiliados.NOM2,
afiliados.EDAD,
afiliados.SEXO,
afiliados.DPTO,
afiliados.MPIO,
afiliados.ZONA
FROM
ordenes ,
afiliados ,
clientes
WHERE
clientes.estado = 1 AND
ordenes.estado = 1 AND
ordenes.idafiliado = afiliados.DOCUMENTO AND
ordenes.tipodoc = afiliados.TIPODOC
GROUP BY
ordenes.tipodoc,
ordenes.idafiliado
