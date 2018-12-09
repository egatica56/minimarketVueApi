var tempModificarMarca = `
<div class="container" align="center">
    <h1>Modificar Marca</h1>
    <br>
    <form>
        <table>
            <tr>
                <td>Id Marca</td>
                <td><input type=number v-model="idMarca" required="" class="form-control"></td>
            </tr>
            
            <tr>
                <td>Nombre Marca</td>
                <td><input type=text v-model="nombreMarca" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Descripcion Marca</td>
                <td><input type=text v-model="descripcionMarca" required="" class="form-control"></td>
            </tr>

            
            <tr align="center">
                
                <td><input class="btn btn-primary" type=button @click="modificar" value="modificar"></td>
                <td><input class="btn btn-primary" type=button @click="limpiar" value="Limpiar"></td>
            </tr>
           
        </table>
    </form>
    <div v-if="mensaje">
        <h3>{{mensaje}}</h3>
    </div>
</div>
`


var modificarMarca = Vue.component('modificar-marca', {
    template: tempModificarMarca,
    data: function () {
        return {
            'idMarca':'',
            'nombreMarca': '',
            'descripcionMarca': '',
            'mensaje':''
        }
    },
    created: function () {
        
    },
    methods: {

        modificar: function () {

            var self = this
            var marcaService = new MarcaService()
            marcaService.modificar(
                self.idMarca,
                self.nombreMarca,
                self.descripcionMarca
                

            )
                .then(function (data) {

                    console.log("Modificado")
                    self.mensaje="Modificado Correctamente"
                    //llamo al metodo limpiar para limpiar los datos.
                    self.limpiar()

                })

        },
        limpiar(){
            this.idMarca=""
            this.nombreMarca=""
            this.descripcionMarca=""
            
        }

    }
})