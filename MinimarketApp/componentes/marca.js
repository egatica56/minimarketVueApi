
var templateMarcas = `
<div class="container">
    <h1>Listado Marcas</h1>
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
                <th>Id Marca</th>
                <th>Nombre marca</th>
                <th>Descripcion</th>
                <th>opcion</th>
            </tr>

            <tr v-for="a in listaFiltrada" align="center">
                <td>{{ a.idMarca }}</td>
                <td>{{ a.nombreMarca }}</td>
                <td>{{ a.descripcionMarca }} </td>
                <td><a href="#" align="center" class="btn btn-primary" @click.prevent="eliminar(a.idMarca)">Eliminar</a></td>
            </tr>

        </table>
        <div v-if="mensaje">
        <h3>{{mensaje}}</h3>
        </div>

    </div>
    <div v-else>
        cargando...
    </div>
</div>
`
//prevent quita la naturaleza del A evita la recarga de la pagina.
var marcas = Vue.component('marca', {
    template: templateMarcas,
    data: function () {
        return {
            marcas: null,
            busqueda: null,
            'mensaje':'mensaje'
        }
    },
    created: function () {
        var self = this
        var servicio = new MarcaService()
        //llamo el metodo del servicio o sea listar.
        servicio
            .listar()
            .then(function (json) {
                self.marcas = json
                console.log(json)
            })

    },
    methods: {
        eliminar(id) {
            var self = this
            var servicio = new MarcaService()
            servicio.eliminar(id)
                .then(function (data) {
                    console.log("eliminado correctamente.")
                    self.mensaje = "Marca Eliminada Correctamente"
                    self.listar()
                })
        },
        listar() {
            var self = this
            var servicio = new MarcaService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .listar()
                .then(function (json) {
                    self.marcas = json
                    console.log(json)
                })

        },
        buscar(id) {
            var self = this
            var servicio = new MarcaService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .buscar(id)
                .then(function (json) {
                    self.marcas = json
                    console.log(json)
                })

        },
        modificar(id) {
            var self = this
            var servicio = new MarcaService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .buscar(id)
                .then(function (json) {
                    self.marcas = json
                    console.log(json)
                })

        }

    },
    //son metodos observados que cambian siempre
    computed: {
        //utilizo el componente animales y despues lo listo en el template en vez del componente animal
        listaFiltrada() {
            if (this.busqueda == null | this.busqueda == "") {
                return this.marcas
            }
            //self es como el contexto de todas las variables existentes o algo asi. pero solo
            //funciona en este metodo.
            var self = this
            //retorna la lista de animales filtrados.
            return this.marcas.filter(function(a) {
                return a.nombreMarca.toLowerCase().includes(self.busqueda.toLowerCase())
            })
        }
    }
})