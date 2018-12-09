
var templateUsuarios = `
<div class="container" align="center">
    <h1>Listado Usuarios</h1>
    <label>Buscar por nombre</label>
    <br>
    <div class="col-md-4" align="center">
        <div class="col-xs-2" align="center">
            <input align="center" class="form-control" id="ex1" type="text" v-model="busqueda">
        </div>
    </div>
    
    <br>
    <div v-if="listaFiltrada" class="container">
    <div v-if="mensaje">
    <h3>{{mensaje}}</h3>
    </div>
        <table class="table">
        
        <tr align="center">
            <th>Id Usuario</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Email</th>
            <th>Direccion</th>
            <th>Username</th>
            <th>Password</th>
            <th>Opcion</th>
        </tr>

        <tr v-for="u in listaFiltrada" align="center">
            <td>{{ u.idUsuario }}</td>
            <td>{{ u.nombres }}</td>
            <td>{{ u.apellidos }} </td>
            <td>{{ u.email }}</td>
            <td>{{ u.direccion }}</td>
            <td>{{ u.username }}</td>
            <td>{{ u.password }}</td>
            <td><a href="#" align="center" class="btn btn-primary" @click.prevent="eliminar(u.idUsuario)">Eliminar</a></td>
        </tr>
    
        </table>
        <!--div v-for="u in listaFiltrada">
            
            <h5>{{ u.idUsuario }}</h5>
            <h5>{{ u.nombres }}</h5>
            <h5>{{ u.apellidos }} </h5>
            <h5>{{ u.email }}</h5>
            <h5>{{ u.direccion }}</h5>
            <h5>{{ u.username }}</h5>
            <h5>{{ u.password }}</h5>
            <a href="#" @click.prevent="eliminar(u.idUsuario)">Eliminar</a>
            <hr>
        </div-->
    </div>
    <div v-else>
        cargando...
    </div>
</div>
`
//prevent quita la naturaleza del A evita la recarga de la pagina.
var usuarios = Vue.component('usuario', {
    template: templateUsuarios,
    data: function () {
        return {
            usuarios: null,
            busqueda: null,
            'mensaje':''
        }
    },
    created: function () {
        var self = this
        var servicio = new UsuarioService()
        //llamo el metodo del servicio o sea listar.
        servicio
            .listar()
            .then(function (json) {
                self.usuarios = json
                console.log(json)
            })

    },
    methods: {
        eliminar(id) {
            var self = this
            var servicio = new UsuarioService()
            servicio.eliminar(id)
                .then(function (data) {
                    console.log("Usuario eliminado correctamente.")
                    self.mensaje = "Usuario eliminado Correctamente"
                    self.listar()
                })
        },
        listar() {
            var self = this
            var servicio = new UsuarioService()
            //llamo el metodo del servicio o sea listar.
            servicio
                .listar()
                .then(function (json) {
                    self.usuarios = json
                    console.log(json)
                })

        }


    },
    //son metodos observados que cambian siempre
    computed: {
        //utilizo el componente animales y despues lo listo en el template en vez del componente animal
        listaFiltrada() {
            if (this.busqueda == null | this.busqueda == "") {
                return this.usuarios
            }
            //self es como el contexto de todas las variables existentes o algo asi. pero solo
            //funciona en este metodo.
            var self = this
            //retorna la lista de animales filtrados.
            return this.usuarios.filter(function (u) {
                return u.nombres.toLowerCase().includes(self.busqueda.toLowerCase())
            })
        }
    }
})