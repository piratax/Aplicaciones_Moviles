package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritox

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoxConActividad
import pe.edu.upeu.asistenciaupeujc.repository.InscritoxRepository
import pe.edu.upeu.asistenciaupeujc.repository.MaterialesxRepository
import javax.inject.Inject

@HiltViewModel
class InscritoViewModel @Inject constructor(
    private val inscrixRepo: InscritoxRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<InscritoxConActividad>> by lazy {
        inscrixRepo.reportarInscritoxes()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addInscritox() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteInscritox(toDelete: InscritoxConActividad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            inscrixRepo.deleteInscritox(toDelete);
        }
    }

}