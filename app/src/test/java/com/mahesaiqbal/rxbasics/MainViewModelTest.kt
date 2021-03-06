package com.mahesaiqbal.rxbasics

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mahesaiqbal.rxbasics.MainViewModel.InstallationStatus.SUCCESS
import com.mahesaiqbal.rxbasics.MainViewModel.LoadingStatus.LOADING
import com.mahesaiqbal.rxbasics.MainViewModel.LoadingStatus.NOT_LOADING
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MainViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @InjectMocks
    private lateinit var classUnderTest: MainViewModel

    @Test
    fun `init set joke list to empty`() {
        val jokes = classUnderTest.jokes.testObserver()

        Truth.assert_()
            .that(jokes.observedValues.first()).isEmpty()
    }

    @Test
    fun `onJokeRequest set correct loading states`() {
        val jokeStatus = classUnderTest.jokeLoadingStatus.testObserver()

        classUnderTest.onJokeRequest()

        Truth.assert_()
            .that(jokeStatus.observedValues)
            .isEqualTo(listOf(LOADING, NOT_LOADING))
    }

    @Test
    fun `onFakeInstall set correct live data values`() {
        val installationStatus = classUnderTest.installation.testObserver()
        val installationLoadingStatus = classUnderTest.installLoadingStatus.testObserver()

        classUnderTest.onFakeInstall()

        Truth.assert_()
            .that(installationStatus.observedValues.first())
            .isEqualTo(SUCCESS)

        Truth.assert_()
            .that(installationLoadingStatus.observedValues)
            .isEqualTo(listOf(LOADING, NOT_LOADING))
    }
}