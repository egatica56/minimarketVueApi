//los componentes tienen dos partes
//template y controlador

var templatePrueba = `
<div>
<h2>Este es un componente</h2>
<hr>
<button @click="aumentar">Presione aqui</button>
<h3>{{ contador }}</h3>
</div>
`
var prueba = Vue.component('prueba', {
    template:templatePrueba,
    data:function() {
        return {
            contador:1
        }
    },
    methods:{
        aumentar() {
            this.contador++;
        }
    }
})