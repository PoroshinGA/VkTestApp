package com.example.vktestapp.app.di

import com.example.vktestapp.app.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

val provideAppModule = module {
    viewModel { MainViewModel(getProductsInfoUseCase = get()) }
}
