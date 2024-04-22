import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cal.CalculatorActions
import com.example.cal.CalculatorOperation
import com.example.cal.CalculatorState

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions) {
        when (action) {
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> clearState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDelete()
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull() ?: return
        val number2 = state.number2.toDoubleOrNull() ?: return

        val result = when (val operation = state.operation) {
            is CalculatorOperation.Add -> number1 + number2
            is CalculatorOperation.Subtract -> number1 - number2
            is CalculatorOperation.Multiply -> number1 * number2
            is CalculatorOperation.Divide -> number1 / number2
            else -> return
        }

        state = state.copy(
            number1 = result.toString().take(15),
            number2 = "",
            operation = null
        )
    }

    private fun performDelete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        when {
            state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank() ->
                state = state.copy(number1 = state.number1 + ".")
            !state.number2.contains(".") && state.number2.isNotBlank() ->
                state = state.copy(number2 = state.number2 + ".")
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null && state.number1.length >= MAX_NUM_LENGTH) return
        if (state.number2.length >= MAX_NUM_LENGTH) return

        if (state.operation == null) {
            state = state.copy(number1 = state.number1 + number)
        } else {
            state = state.copy(number2 = state.number2 + number)
        }
    }

    private fun clearState() {
        state = CalculatorState()
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}
