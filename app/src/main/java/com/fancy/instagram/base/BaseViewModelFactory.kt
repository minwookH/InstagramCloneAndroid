package com.fancy.instagram.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fancy.instagram.application.InstagramApp
import java.lang.reflect.InvocationTargetException

class BaseViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                val appApplication = InstagramApp.appContext
                val viewModel = modelClass.getConstructor(InstagramApp::class.java).newInstance(appApplication)
                (viewModel as BaseViewModel).onCreate()
                return viewModel
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }

        return super.create(modelClass)
    }
}