
var templateProductos = `
<div class="container">
    <h1>Listado productos</h1>
    <label>Buscar por nombre</label>
    <div class="col-md-4" align="center">
        <div class="col-xs-2" align="center">
            <input align="center" class="form-control" id="ex1" type="text" v-model="busqueda">
        </div>
    </div>
    </br>
    <div v-if="listaFiltrada">
    <div v-if="mensaje">
        <h3>{{mensaje}}</h3>
    </div>

        <table class="table">
            <tr align="center">
                <th>SKU Producto</th>
                <th>Nombre Prodcuto</th>
                <th>Precio Producto</th>
                <th>Stock Producto</th>
                <th>Descripcion Producto</th>
                <th>Categoria Producto</th>    
                <th>Marca Producto</th>        
                <th>opcion</th>
            </tr>

            <tr v-for="a in listaFiltrada" align="center">
                <td>{{ a.sku }}</td>
                <td>{{ a.nombreProduto }}</td>
                <td>{{ a.precioProducto }} </td>
                <td>{{ a.stockProducto }} </td>
                <td>{{ a.descripcionProduto }} </td>
                <td>{{ a.categoria.nombreCategoria }} </td>
                <td>{{ a.marca.nombreMarca }} </td>
                <td><a href="#" align="center" class="btn btn-primary" @click.prevent="eliminar(a.sku)">Eliminar</a></td>
            </tr>

        </table>
        
    </div>
    <div v-else>
        cargando...
    </div>
</div>
`
//prevent quita la naturaleza del A evita la recarga de la pagina.
var productos = Vue.component('producto', {
    template: templateProductos,
    data: function () {
        return {
            productos: null,
            busqueda: null,
            'mensaje':''
        }
    },
    created: function () {
        var self = this
        var servicio = new ProductoService()
        //llamo el metodo del servicio o sea listar.
        servicio
            .listar()
            .then(function (json) {
                self.productos = json
                console.log(json)
            })

    },
    methods: {
        eliminar(id) {
            var self = this
            var servicio = new ProductoService()
            servicio.eliminar(id)
                .then(function (data) {
                    console.log("Producto eliminado correctamente.")
                    self.mensaje = "Producto Eliminado Correctamente"
                    self.listar()
                })
        },
        listar() {
            var self = this
            var servicio = new ProductoService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .listar()
                .then(function (json) {
                    self.productos = json
                    console.log(json)
                })

        },
        buscar(id) {
            var self = this
            var servicio = new ProductoService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .buscar(id)
                .then(function (json) {
                    self.productos = json
                    console.log(json)
                })

        }
       

    },
    //son metodos observados que cambian siempre
    computed: {
        //utilizo el componente productos y despues lo listo en el template en vez del componente animal
        listaFiltrada() {
            if (this.busqueda == null | this.busqueda == "") {
                return this.productos
            }
            //self es como el contexto de todas las variables existentes o algo asi. pero solo
            //funciona en este metodo.
            var self = this
            //retorna la lista de productos filtrados.
            return this.productos.filter(function(a) {
                return a.nombreProducto.toLowerCase().includes(self.busqueda.toLowerCase())
            })
        }
    }
})