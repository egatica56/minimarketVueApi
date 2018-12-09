//declararemos las rutas de la aplicacion.
//routes si no no funciona el route-link
var routes = [
    {
        'path': '/',
        'redirect': '/listar'

    },
    {
        //path es lo mismo que ruta
        'path': '/listar',
        //el componente que se va a cargar, desde la variable que almacena el componente
        'component': marcas

    },
    {
        'path': '/agregar',
        'component': agregarMarca

    },
    {
        'path': '/modificar',
        'component': modificarMarca

    },
    {
        'path': '/agregarUsuario',
        'component': agregarUsuario

    },
    {
        'path': '/listarUsuario',
        'component': usuarios
    },
    {
        'path': '/modificarUsuario',
        'component': modificarUsuario
    },
    {
        'path': '/agregarProducto',
        'component': agregarProducto
    }
    ,
    {
        'path': '/listarProducto',
        'component': productos
    },
    {
         'path': '/modificarProducto',
         'component': modificarProducto
    }

]

//se instancia el enrutador
var router = new VueRouter({
    routes
})

//se entrega el enrutador a la aplicacion
var app = new Vue({
    //'el': '#app'
    router
    //.$ mount monta la aplicacion.
}).$mount('#app')