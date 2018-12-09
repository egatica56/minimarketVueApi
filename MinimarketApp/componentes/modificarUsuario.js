var tempModificarUsuario = `
<div class="container" align="center">
    <h1>Modificar Usuario</h1>
    <br>
    <form>
    
        <table>
            <tr>
                <td>id Usuario</td>
                <td><input type=number v-model="idUsuario" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Nombres Usuario</td>
                <td><input type=text v-model="nombres" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Apellidos Usuario</td>
                <td><input type=text v-model="apellidos" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Direccion Usuario</td>
                <td><input type=text v-model="direccion" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Email Usuario</td>
                <td><input type=text v-model="email" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Username</td>
                <td><input type=text v-model="username" required="" class="form-control"></td>
            </tr>

            <tr>
                <td>Password</td>
                <td><input type=text v-model="password" required="" class="form-control"></td>
            </tr>



            
            <tr align="center">
                
                <td><input align="center" class="btn btn-primary" type=button @click="modificar" value="Modificar"></td>
                <td><input align="center" class="btn btn-primary" type=button @click="limpiar" value="Limpiar"></td>
            </tr>
           
        </table>
    </form>
    <div v-if="mensaje">
        <h3>{{mensaje}}</h3>
    </div>
</div>
`


var modificarUsuario = Vue.component('modificar-usuario', {
    template: tempModificarUsuario,
    data: function () {
        return {
        'idUsuario': '',
        'username': '',
        'password': '',
        'email': '',
        'nombres': '',
        'apellidos': '',
        'direccion': '',
        'mensaje':''
        }
    },
    created: function () {
        
    },
    methods: {

        modificar: function () {

            var self = this
            var usuarioService = new UsuarioService()
            usuarioService.modificar(
                self.idUsuario,
                self.username,
                self.password,
                self.email,
                self.nombres,
                self.apellidos,
                self.direccion,
                
                

            )
                .then(function (data) {

                    console.log("Usuario Modificado")
                    self.mensaje="Usuario Modificado Correctamente"
                    //llamo al metodo limpiar para limpiar los datos.
                    self.limpiar()

                })

        },
        limpiar(){
            this.idUsuario=""
            this.username=""
            this.password=""
            this.email=""
            this.nombres=""
            this.apellidos=""
            this.direccion=""
            
        }

    }
})