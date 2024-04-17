import {createSlice} from "@reduxjs/toolkit";
import {UserEntity} from "@/entities/user/models/UserEntity";
import {userAPI} from "@/entities/user";

interface AuthState {
  userData?: UserEntity
}

const initialState: AuthState = {}

export const authSlice = createSlice({
  name: "authSlice",
  initialState,
  reducers: {

  },
  extraReducers: builder =>
    builder
      .addMatcher(userAPI.endpoints.login.matchFulfilled, (state, { payload }) => {
        state.userData = payload
      })
      .addMatcher(userAPI.endpoints.registration.matchFulfilled, (state, { payload }) => {
        state.userData = payload
      })
      .addMatcher(userAPI.endpoints.logout.matchFulfilled, (state, {  }) => {
        state.userData = undefined
      })
})

export const {  } = authSlice.actions
