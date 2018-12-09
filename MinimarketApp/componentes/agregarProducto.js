var templateAgregarProducto = `
<div class="container" align="center">
    <h1>Agregar Marca</h1>
    <br>
    <form>
        <table>
            <tr>
                <td>SKU Producto</td>
                <td><input type=number v-model="sku" required="" class="form-control"></td>
            </tr>    


            <tr>
                <td>Nombre producto</td>
                <td><input type=text v-model="nombreProducto" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Precio Producto</td>
                <td><input type=number v-model="precioProducto" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Stock Producto</td>
                <td><input type=number v-model="stockProducto" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Descripcion Producto</td>
                <td><input type=text v-model="descripcionProducto" required="" class="form-control"></td>
            </tr>


            <tr>
                <td>Marca Id</td>
                <td>
                    <select v-model="marcaId" required="" class="form-control">
                    <option value="">Seleccionar</option>
                    
                    <option v-for="m in marcas"  v-bind:value="m.idMarca">{{m.nombreMarca}} </option>

                    </select>
                </td>
            </tr>

            <tr>
                <td>Categoria Id</td>
                <td>
                    <select v-model="categoriaId" required="" class="form-control">
                    <option value="">Seleccionar</option>
                    
                    <option v-for="c in categorias"  v-bind:value="c.idCategoria">{{c.nombreCategoria}} </option>

                    </select>
                </td>
            </tr>


            
            <tr align="center">
                
                <td><input class="btn btn-primary" type=button @click="guardar" value="Guardar"></td>
                <td><input class="btn btn-primary" type=button @click="limpiar" value="Limpiar"></td>
            </tr>
           
        </table>
    </form>
    <div v-if="mensaje">
        <h3>{{mensaje}}</h3>
    </div>
</div>
`


var agregarProducto = Vue.component('agregar-producto', {
    template: templateAgregarProducto,
    data: function () {
        return {
            'sku': '',
            'categorias': null,
            'marcas': null,
            'categoriaId': '',
            'marcaId': '',
            'nombreProducto': '',
            'precioProducto': '',
            'stockProducto': '',
            'descripcionProducto': '',
            'mensaje': ''
        }
    },
    created: function () {
        var self = this
        var serviceMarca = new MarcaService()
        var serviceCategoria = new CategoriaService()

        serviceMarca.listar()
            .then(function (data) {
                self.marcas = data

            })

        serviceCategoria.listar()
            .then(function (data) {
                self.categorias = data

            })
    },
    methods: {

        guardar: function () {

            var self = this
            var productoService = new ProductoService()
            productoService.agregar(
                self.sku,
                self.categoriaId,
                self.marcaId,
                self.nombreProducto,
                self.precioProducto,
                self.stockProducto,
                self.descripcionProducto


            )
                .then(function (data) {

                    console.log("Guardado")
                    self.mensaje = "Producto Guardado Correctamente"
                    //llamo al metodo limpiar para limpiar los datos.
                    self.limpiar()

                })

        },
        limpiar() {
                this.sku=""
                this.categoriaId=""
                this.marcaId=""
                this.nombreProducto=""
                this.precioProducto=""
                this.stockProducto=""
                this.descripcionProducto=""

        }

    }
})