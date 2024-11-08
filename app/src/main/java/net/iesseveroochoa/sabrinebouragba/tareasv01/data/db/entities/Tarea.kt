package net.iesseveroochoa.sabrinebouragba.tareasv01.data.db.entities

data class Tarea(
    var id: Long? = null, // id único
    val categoria: Int,
    val prioridad: Int,
    val img: String,
    val pagado: Boolean,
    val estado: Int,
    val valoracion: Int,
    val tecnico: String,
    val descripcion: String
) {
    companion object {
        var idContador = 1L // Inicializa el contador en 1
        private fun generarId(): Long {
            return idContador++
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is Tarea) && (this.id == other.id)
    }

    constructor( // Constructor sin ID (id generado automáticamente)
        categoria: Int,
        prioridad: Int,
        img: String,
        pagado: Boolean,
        estado: Int,
        valoracion: Int,
        tecnico: String,
        descripcion: String
    ) : this(
        id = generarId(),
        categoria = categoria,
        prioridad = prioridad,
        img = img,
        pagado = pagado,
        estado = estado,
        valoracion = valoracion,
        tecnico = tecnico,
        descripcion = descripcion
    )

}
