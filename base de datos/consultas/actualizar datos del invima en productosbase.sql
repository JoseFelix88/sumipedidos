UPDATE db_sumipedidos.productosbase producto,
db_invima.vencidos invima
SET PRODUCTO.unidadmedida = invima.UNIDADMEDIDA,

PRODUCTO.cantidadconcentrada = invima.CANTIDADconcentrada,
PRODUCTO.formafarmaceutica = invima.`FORMAFARMACÉUTICA`

WHERE
PRODUCTO.cum = CONCAT(invima.EXPEDIENTE,'-',invima.CONSECUTIVO);

UPDATE db_sumipedidos.productosbase producto,
db_invima.renovaciones invima
SET PRODUCTO.unidadmedida = invima.UNIDADMEDIDA,

PRODUCTO.cantidadconcentrada = invima.CANTIDADconcentrada,
PRODUCTO.formafarmaceutica = invima.`FORMAFARMACÉUTICA`

WHERE
PRODUCTO.cum = CONCAT(invima.EXPEDIENTE,'-',invima.CONSECUTIVO);


UPDATE db_sumipedidos.productosbase producto,
db_invima.otrosestados invima
SET PRODUCTO.unidadmedida = invima.UNIDADMEDIDA,

PRODUCTO.cantidadconcentrada = invima.CANTIDADconcentrada,
PRODUCTO.formafarmaceutica = invima.`FORMAFARMACÉUTICA`

WHERE
PRODUCTO.cum = CONCAT(invima.EXPEDIENTE,'-',invima.CONSECUTIVO);