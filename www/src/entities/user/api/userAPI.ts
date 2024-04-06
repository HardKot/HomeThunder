import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { AxiosMethod } from "@/shared/api/axiosBaseQuery";
import { RegistrationForm } from "@/entities/user/models/RegistrationForm";
import { UserEntity } from "@/entities/user/models/UserEntity";
import { UserLogin } from "@/entities/user/models/UserLogin";

export const userAPI = createApi({
  reducerPath: "userAPI",
  baseQuery: fetchBaseQuery({ baseUrl: "https://api.localhost/" }),
  endpoints: (build) => ({
    registration: build.mutation<UserEntity, RegistrationForm>({
      query: (data) => ({ url: "/user", method: AxiosMethod.POST, data }),
    }),
    login: build.mutation<UserEntity, UserLogin>({
      query: (data) => ({ url: "/login", method: AxiosMethod.POST, data }),
    }),
  }),
});

export const { useRegistrationMutation, useLoginMutation } = userAPI;
