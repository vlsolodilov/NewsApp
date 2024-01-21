package com.solodilov.newsapp.presentation.common

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.solodilov.newsapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block.invoke(it) }
        }
    }

    fun Flow<Throwable>.connectErrorData() {
        lifecycleScope.launch {
            sample(1000L).collect {
                if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun Fragment.launchAndRepeatOnStart(block: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block.invoke()
            }
        }
    }
}