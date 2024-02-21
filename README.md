
# Gestion de Inventario y facturación
1.**Creacion del producto:**
Inicialmente se debe de crear un producto, recordar que el stock que
pide es el stock inicial, por lo que debe ser mayor.
2.**Crear Customer**
posteriormente debe agregar un Customer:
3.**Crear venta (Sale)**
Una vez listo el producto y el customer, se debe crear una venta
para esto, se le requerira el id del Customer anteriormente creado, y 
el body de la factura con esta estructura:

```json
{
"id": 0,
"date_of_sale": "2024-02-21",
"products": [
{
"id": 1,
"nameProduct": "limon",
"stock": 2
}, 
  {
    "id": 2,
    "nameProduct": "cereza",
    "stock": 1
  }
]
}
```

en el vector de productos se deben añadir los productos previamente creados
recordar que el ID de productos deben coincidir con los previamente creados,
y en el stock se indica la cantidad de productos que desee llevar.

El programa valida que el stock que que se pone a la hora de crear una venta (sale),
no sea mayor al que se le asigno en el stock inicial del producto.

Una vez realizada la venta (sale), se descontara en el stock del producto.

#### Robinson Aguilar - cod 202010499
