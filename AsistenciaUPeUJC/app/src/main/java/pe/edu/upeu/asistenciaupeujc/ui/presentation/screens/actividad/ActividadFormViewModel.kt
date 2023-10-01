package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.repository.ActividadRepository
import javax.inject.Inject

@HiltViewModel
class ActividadFormViewModel @Inject constructor(
    private val activRepo: ActividadRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getActividad(idX: Long): LiveData<Actividad> {
        return activRepo.buscarActividadId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addActividad(actividad: Actividad){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", actividad.toString())
            activRepo.insertarActividad(actividad)
        }
    }
    fun editActividad(actividad: Actividad){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteActividad(actividad)
        }
    }
}