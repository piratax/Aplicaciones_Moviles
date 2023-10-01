package pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form


import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.github.k0shk0sh.compose.easyforms.EasyFormsErrorState
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.github.k0shk0sh.compose.easyforms.EasyFormsState
import com.github.k0shk0sh.compose.easyforms.EasyFormsValidationType
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel


class MyEasyFormsCustomDateState(
    private val defaultValue: String = "",
    easyFormsValidationType: EasyFormsValidationType,
) : EasyFormsState<MutableState<String>, String>() {
    private val _isOpen = mutableStateOf(false)
    val isOpen: State<Boolean> = _isOpen
    val onDismissed: () -> Unit = {
        _isOpen.value = false
    }
    val onClick: () -> Unit = {
        _isOpen.value = true
    }
    override val state: MutableState<String> = mutableStateOf(defaultValue)
    override val onValueChangedCallback: (String) -> Unit = {
        Log.i("DATAX", "Holas"+it)
        state.value = it
        _isOpen.value = false
        errorState.value = when (it!=null) {
            true -> EasyFormsErrorState.VALID
            false -> EasyFormsErrorState.INVALID
        }
    }
    override fun mapToResult(key: Any): EasyFormsResult {
        return MyEasyFormsCustomStringResult(
            key = key,
            easyFormsErrorState = errorState.value,
            value = state.value,
        )
    }
    override fun saveState(bundle: Bundle) {
        super.saveState(bundle)
        bundle.putString("value", state.value)
        bundle.putBoolean("open", _isOpen.value)
    }
    override fun restoreState(bundle: Bundle) {
        super.restoreState(bundle)
        state.value = bundle.getString("value", defaultValue)
        _isOpen.value = bundle.getBoolean("open", false)
    }
}

class MyEasyFormsCustomStringState(
    private val defaultValue: String = "",
    private val validData: List<ComboModel>,
) : EasyFormsState<MutableState<String>, String>() {
    private val _isOpen = mutableStateOf(false)
    val isOpen: State<Boolean> = _isOpen
    val onDismissed: () -> Unit = {
        _isOpen.value = false
    }
    val onClick: () -> Unit = {
        _isOpen.value = true
    }
    override val state: MutableState<String> = mutableStateOf(defaultValue)
    override val onValueChangedCallback: (String) -> Unit = {
        Log.i("VERRRR", it.toString())
        state.value = it
        _isOpen.value = false
        errorState.value = when (it!=null) {
            true -> EasyFormsErrorState.VALID
            false -> EasyFormsErrorState.INVALID
        }
    }
    override fun mapToResult(key: Any): EasyFormsResult {
        return MyEasyFormsCustomStringResult(
            key = key,
            easyFormsErrorState = errorState.value,
            value = state.value,
        )
    }
    override fun saveState(bundle: Bundle) {
        super.saveState(bundle)
        bundle.putString("value", state.value)
        bundle.putBoolean("open", _isOpen.value)
    }
    override fun restoreState(bundle: Bundle) {
        super.restoreState(bundle)
        state.value = bundle.getString("value", defaultValue)
        _isOpen.value = bundle.getBoolean("open", false)
    }
}

data class MyEasyFormsCustomStringResult(
    override val key: Any,
    override val easyFormsErrorState: EasyFormsErrorState,
    override val value: String,
) : EasyFormsResult.GenericStateResult<String>(
    key = key,
    easyFormsErrorState = easyFormsErrorState,
    value = value,
)

