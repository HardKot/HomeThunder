import {PropsWithChildren} from "react";
import {Provider} from "react-redux";
import {reduxWrapper} from "@/app/reduxStore";
import type {AppProps} from "next/app";
import {AppCacheProvider} from "@mui/material-nextjs/v13-pagesRouter";

export const StateStoreProvider = ({children, Component, ...rest}: PropsWithChildren & AppProps) => {
  const {store, props} = reduxWrapper.useWrappedStore(rest)

  return (
    <AppCacheProvider {...props}>
      <Provider store={store}>
        {children}
      </Provider>
    </AppCacheProvider>)
}
