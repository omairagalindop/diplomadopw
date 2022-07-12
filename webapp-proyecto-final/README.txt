Buen día Ingeniero Rafael

A continuación anexo unas especificaciones para poder desplegar el proyecto final de forma adecuada.

En la carpeta bd se encuentram un archivo con los scripts de creación de las tablas, y algunos inserts.

1. En la bd de datos en la tabla usuarios hay creados 2 usuarios:
	-username: admin password: 12345 rol admin
	-username: proveedor1 password: 12345 rol proveedor
2.Si se ingresa como administrador se podrá acceder a la CRUD de productos, a la creación de pedidos (Sin ítems aún aunque en la base de datos ya están las tablas y las relaciones necesarias para asociar un item_pedido con el pedido, al igual que en backend ya están creadas las clases POJO para ello), se pueden visualizar las ofertas a la entrega de pedidos que han realizado los proveedores. (Me hace falta aún el servlet para seleccionar la mejor oferta según el menor tiempo de entrega, ya tengo contemplada la consulta para ello)

3. Como proveedor se puede visualizar los pedidos disponibles para entregas, y realizar la oferta para la entrega de pedido ingresando tiempo propuesto de entrega y valor del domicilio. El proveedor también puede visualizar la lista de ofertas hechas y el estado, aunque me hace falta filtrarla por proveedor. (espero poder asociar el usuario a un registro de proveedor y acá hacer el filtro). Por el momento la tabla de proveedores no está enlazada en Backend, la intención es obtener el nombre del proveedor asociado al usuario, al igual que el número de teléfono.

4. No se está actualizando el inventario aunque ya están las tablas, se debe disminuir el stock dependiendo de los productos de cada pedido entregado. El valor total del pedido está en 0, porque no se están cargando aún los ítems del pedido.



