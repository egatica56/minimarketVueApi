class RazaService {

    constructor() { }

    listar() {
        var url = config.url + "/raza"

        return fetch(url)
        //variable respuesta es la respuesta que nos entrega el servidor.
            .then(function (respuesta) {
                return respuesta.json()

            })




    }







}