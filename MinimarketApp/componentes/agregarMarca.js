var templateAgregarMarca = `
<div class="container" align="center">
    <h1>Agregar Marca</h1>
    <br>
    <form>
        <table>

            <tr>
                <td>Nombre Marca</td>
                <td><input type=text v-model="nombreMarca" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Descripcion Marca</td>
                <td><input type=text v-model="descripcionMarca" required="" class="form-control"></td>
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


var agregarMarca = Vue.component('agregar-marca', {
    template: templateAgregarMarca,
    data: function () {
        return {
            'nombreMarca': '',
            'descripcionMarca': '',
            'mensaje':''
        }
    },
    created: function () {
        
    },
    methods: {

        guardar: function () {

            var self = this
            var marcaService = new MarcaService()
            marcaService.agregar(
                self.nombreMarca,
                self.descripcionMarca
                

            )
                .then(function (data) {

                    console.log("Guardado")
                    self.mensaje="Guardado Correctamente"
                    //llamo al metodo limpiar para limpiar los datos.
                    self.limpiar()

                })

        },
        limpiar(){
            this.nombreMarca=""
            this.descripcionMarca=""
            
        }

    }
})