import { configureStore, Store } from "@reduxjs/toolkit";
import { authSlice, userAPI } from "@/entities/user";

export const makeStore = () =>
  configureStore({
    reducer: {
      [authSlice.reducerPath]: authSlice.reducer,
      [userAPI.reducerPath]: userAPI.reducer,
    },
    middleware: (getDefaultMiddleware) =>
      getDefaultMiddleware().concat(userAPI.middleware),
  });

// Infer the type of makeStore
export type AppStore = ReturnType<typeof makeStore>;
// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<AppStore["getState"]>;
export type AppDispatch = AppStore["dispatch"];
