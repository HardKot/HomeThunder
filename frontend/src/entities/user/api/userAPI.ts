import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { RegistrationForm } from "@/entities/user/models/RegistrationForm";
import { UserEntity } from "@/entities/user/models/UserEntity";
import { UserLogin } from "@/entities/user/models/UserLogin";

export const userAPI = createApi({
  reducerPath: "userAPI",
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080" }),
  endpoints: (build) => ({
    registration: build.mutation<UserEntity, RegistrationForm>({
      query: (body) => ({
        url: "/registration",
        method: 'POST',
        body: {
          ...body,
          birthday: body.birthday.format("YYYY-MM-DD"),
        },
      }),
    }),
    login: build.mutation<UserEntity, UserLogin>({
      query: (body) => ({
        url: "/login",
        // method: AxiosMethod.POST,
        body,
      }),
    }),
    logout: build.mutation<null, UserLogin>({
      query: (body) => ({
        url: "/logout",
        // method: AxiosMethod.POST,
        body,
      }),
    }),
  }),
});

export const { useRegistrationMutation, useLoginMutation } = userAPI;
