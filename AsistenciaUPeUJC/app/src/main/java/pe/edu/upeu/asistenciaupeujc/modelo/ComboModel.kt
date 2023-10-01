package pe.edu.upeu.asistenciaupeujc.modelo

data class ComboModel(val code:String, val name:String): PickerValue(){
    override fun searchFilter(query: String): Boolean {
        return this.name.startsWith(query)
    }
}

abstract class  PickerValue{
    abstract fun searchFilter(query:String):Boolean
}
