import {createApi} from '@reduxjs/toolkit/query/react'
import {axiosBaseQuery, AxiosMethod} from "@/shared/api/axiosBaseQuery";
import {RegistrationForm} from "@/entities/user/models/RegistrationForm";
import {UserEntity} from "@/entities/user/models/UserEntity";

export const userAPI = createApi({
  reducerPath: 'userAPI',
  baseQuery: axiosBaseQuery({baseUrl: 'https://api.localhost/',}),
  endpoints: build => ({
    registration: build.mutation<UserEntity, RegistrationForm>({
      query: (data) => ({url: "/", method: AxiosMethod.POST, data})
    })

  })
})


export const { useRegistrationMutation } = userAPI
