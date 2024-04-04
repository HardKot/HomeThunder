import type { AppProps } from "next/app";
import {CombineProvider} from "@/app/providers/CombineProvider";

export const AppEntary = (props: AppProps) => {
  const { Component, ...rest } = props
  return (
    <CombineProvider {...props}>
      <Component {...rest} />
    </CombineProvider>
  )
}
