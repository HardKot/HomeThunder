import {PropsWithChildren} from "react";
import {ThemeProvider} from "@/app/providers/ThemeProvider";
import {LocalizationProvider} from "@/app/providers/LocalizationProvider";
import {StateStoreProvider} from "@/app/providers/StateStoreProvider";
import type {AppProps} from "next/app";

export const CombineProvider = ({children, ...props}: PropsWithChildren & AppProps) => (
  <StateStoreProvider {...props}>
    <ThemeProvider>
      <LocalizationProvider>
        {children}
      </LocalizationProvider>
    </ThemeProvider>
  </StateStoreProvider>

)
