package co.develhope.meteoapp.features.ui

import co.develhope.meteoapp.features.ui.home.HomeViewModel
import co.develhope.meteoapp.features.ui.search.SearchCityViewModel
import co.develhope.meteoapp.features.ui.today.TodayViewModel
import co.develhope.meteoapp.features.ui.tomorrow.TomorrowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel{ HomeViewModel(get()) }
    viewModel{ TodayViewModel(get()) }
    viewModel{ TomorrowViewModel(get()) }
    viewModel{ SearchCityViewModel(get()) }
}