
class CategoriaService {

    constructor() {

    }

    listar() {
        var url = config.url + "/categoria"

        return fetch(url)
            .then(function (respuesta) {
                //convertimos la respuesta
                //en un json
                return respuesta.json()
            })
    }

    //metodo agregar
    agregar(nombrecategoria, descripcioncategoria) {
        //la url de la api
        var url = config.url + "/categoria"
        //el json que captura la data del front.
        var data =
        {
            'nombrecategoria': nombrecategoria,
            'descripcioncategoria': descripcioncategoria,
         

        }
        //seteo el metodo de envio de la data.
        return fetch(url, {
            'method': 'POST',
            'body': JSON.stringify(data),
            //añado los headers y el tipo de data
            'headers': {
                'Content-type': 'application/json'
            }
        })
            .then(function (respuesta) {
                //convertimos la respuesta del servidor en un json
                return respuesta.json
            })


    }

    modificar(idcategoria,nombrecategoria, descripcioncategoria) {
        //la url de la api
        var url = config.url + "/categoria/"+idcategoria
        //el json que captura la data del front.
        var data =
        {   'idcategoria':idcategoria,
            'nombrecategoria': nombrecategoria,
            'descripcioncategoria': descripcioncategoria,
         

        }
        //seteo el metodo de envio de la data.
        return fetch(url, {
            'method': 'PUT',
            'body': JSON.stringify(data),
            //añado los headers y el tipo de data
            'headers': {
                'Content-type': 'application/json'
            }
        })
            .then(function (respuesta) {
                //convertimos la respuesta del servidor en un json
                return respuesta.json
            })


    }



    buscar(id) {
        var url = config.url + "/categoria/"+id

        return fetch(url, {
            'method': 'GET'


        })
            .then(function (respuesta) {
                return respuesta.json

            })

    }


    eliminar(id) {
        var url = config.url + "/categoria/"+id

        return fetch(url, {
            'method': 'DELETE'


        })
            .then(function (respuesta) {
                return respuesta.json

            })

    }

}