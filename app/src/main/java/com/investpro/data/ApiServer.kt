package com.investpro.data

import com.investpro.domain.model.basedto.BaseDto
import retrofit2.http.GET

interface ApiServer {
    @GET ("529/db.json")
    suspend fun getDataDb () : BaseDto
}