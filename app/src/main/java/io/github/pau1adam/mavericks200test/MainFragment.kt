package io.github.pau1adam.mavericks200test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CounterState(@PersistState val count: Int = 0): MavericksState

class CounterViewModel(initialState: CounterState): MavericksViewModel<CounterState>(initialState) {
    fun incrementCount() = setState { copy(count = count + 1) }
}

class MainFragment : Fragment(R.layout.main_fragment), MavericksView {

    private val viewModel: CounterViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        counter.setOnClickListener { viewModel.incrementCount() }
    }

    override fun invalidate() = withState(viewModel) { state ->
        counter.text = state.count.toString()
    }
}
