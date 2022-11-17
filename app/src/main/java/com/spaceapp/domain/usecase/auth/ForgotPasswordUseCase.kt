package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task as gmsTask
import com.huawei.hmf.tasks.Task as hmsTask
import com.spaceapp.core.common.TaskResult
import com.spaceapp.data.repository.FirebaseAuthRepositoryImpl
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
    private val hmsAuthRepositoryImpl: HmsAuthRepositoryImpl,
) {
    fun firebaseAuth(forgotPassword: ForgotPassword): Flow<TaskResult<gmsTask<Void>>> = flow {
        try {
            emit(TaskResult.Success(data = firebaseAuthRepositoryImpl.forgotPassword(forgotPassword = forgotPassword)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(forgotPassword: ForgotPassword): Flow<TaskResult<hmsTask<Void>>> = flow {
        try {
            emit(TaskResult.Success(data = hmsAuthRepositoryImpl.forgotPassword(forgotPassword = forgotPassword)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}