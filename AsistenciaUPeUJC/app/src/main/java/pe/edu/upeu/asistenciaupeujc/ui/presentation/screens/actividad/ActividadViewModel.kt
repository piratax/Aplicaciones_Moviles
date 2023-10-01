package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.repository.ActividadRepository
import javax.inject.Inject

@HiltViewModel
class ActividadViewModel @Inject constructor(
    private val activRepo: ActividadRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<Actividad>> by lazy {
        activRepo.reportarActividades()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addActividad() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteActividad(toDelete: Actividad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            activRepo.deleteActividad(toDelete);
        }
    }

}