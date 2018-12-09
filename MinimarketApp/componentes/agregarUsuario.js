var tempAgregarUsuario = `
<div>
    <h1>Agregar Usuario</h1>
    <br>
    <br>
    <form>
        <table>

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


var agregarUsuario = Vue.component('agregar-usuario', {
    template: tempAgregarUsuario,
    data: function () {
        return {
       
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

        guardar: function () {

            var self = this
            var usuarioService = new UsuarioService()
            usuarioService.agregar(
                self.username,
                self.password,
                self.email,
                self.nombres,
                self.apellidos,
                self.direccion,
                
                

            )
                .then(function (data) {

                    console.log("Usuario Guardado")
                    self.mensaje="Usuario Guardado Correctamente"
                    //llamo al metodo limpiar para limpiar los datos.
                    self.limpiar()

                })

        },
        limpiar(){
            this.username=""
            this.password=""
            this.email=""
            this.nombres=""
            this.apellidos=""
            this.direccion=""
            
        }

    }
})