"use client"
import { PropsWithChildren } from "react";
import { Provider } from "inversify-react"
import { TYPES } from "@/ioc";
import { Container } from "inversify";

interface IocProviderProps extends PropsWithChildren {
  csrfToken: string
}

export const IocProvider = ({ children, csrfToken }: IocProviderProps) => (
  <Provider container={() => {
    const appContainer = new Container();
    appContainer.bind(TYPES.CSRFToken).toConstantValue(csrfToken);
    return appContainer;
  }}
  >
    {children}
  </Provider>
)
