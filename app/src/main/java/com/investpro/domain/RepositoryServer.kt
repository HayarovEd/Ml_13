package com.investpro.domain

import com.investpro.data.Resource
import com.investpro.domain.model.basedto.BaseDto

interface RepositoryServer {
    suspend fun getDataDb() : Resource<BaseDto>
}