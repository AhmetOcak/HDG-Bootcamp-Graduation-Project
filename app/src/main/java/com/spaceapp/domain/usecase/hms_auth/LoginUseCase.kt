package com.spaceapp.domain.usecase.hms_auth

import com.huawei.agconnect.auth.SignInResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.AuthRepositoryImpl
import com.spaceapp.domain.model.Login
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepositoryImpl) {

    operator fun invoke(login: Login): Flow<Result<Task<SignInResult>>> = flow {
        try {
            emit(Result.Success(data = authRepository.login(login = login)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}