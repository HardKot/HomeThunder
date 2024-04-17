"use client"
import {PropsWithChildren, useRef} from "react";
import { Provider } from "react-redux";
import {AppStore, makeStore } from "@/app/reduxStore";

export const StateStoreProvider = ({children}: PropsWithChildren) => {
  const storeRef = useRef<AppStore>()
  if (!storeRef.current) {
    storeRef.current = makeStore()
  }

  return (
      <Provider store={storeRef.current}>{children}</Provider>
  );
};
